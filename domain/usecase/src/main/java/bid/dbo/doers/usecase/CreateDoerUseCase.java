package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import bid.dbo.doers.domain.events.DoerSaveFailed;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.core.publisher.Mono;

import static bid.dbo.doers.domain.common.UniqueIDGenerator.uuid;
import static java.time.Duration.ofSeconds;
import static reactor.core.publisher.Mono.from;
import static reactor.core.publisher.Mono.when;

@RequiredArgsConstructor
public class CreateDoerUseCase {

    private final DoersRepository doersService;
    private final DoersRepository doersDb;
    private final DomainEventBus eventBus;

    public Mono<Void> createDoer(Doer doer) {
        return uuid()
            .map(id -> doer.toBuilder().id(id).build())
            .flatMap(newDoer -> {
                final Mono<Doer> saveToService = doersService.save(newDoer)
                    .timeout(ofSeconds(5))
                    .onErrorResume(err -> notifyFailOnSave(doer));
                return when(doersDb.save(newDoer), saveToService);
            });
    }

    private Mono<Doer> notifyFailOnSave(Doer doer) {
        return from(eventBus.emit(new DoerSaveFailed(doer.getId(), doer))).thenReturn(doer);
    }

}
