package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofSeconds;

@RequiredArgsConstructor
public class RetryRemoteDoerCreationUseCase {

    private final DoersRepository doersService;

    public Mono<Void> retryCreateDoer(Doer doer) {
        return doersService.save(doer)
            .timeout(ofSeconds(15))
            .retryBackoff(4, ofSeconds(3));
    }
}
