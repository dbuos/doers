package bid.dbo.doers.repository;

import bid.dbo.doers.domain.Doer;
import bid.dbo.doers.domain.DoersRepository;
import bid.dbo.doers.repository.json.DoerJson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.publisher.Flux.fromIterable;

@RequiredArgsConstructor
@Component
public class DynamoDoersRepository implements DoersRepository {

    private final WebClient client;

    @Override
    public Flux<Doer> findAll() {
        return null;
    }

    @Override
    public Mono<Doer> findById(String id) {
        return client.get()
            .uri("/doers/" + id)
            .retrieve().bodyToMono(DoerJson.class)
            .cast(Doer.class);
    }

    @Override
    public Flux<Doer> searchBySkill(String skillName) {
        return client.get().uri("/skills?skill="+skillName)
            .retrieve().bodyToMono(DoersList.class)
            .flatMapMany(doersList -> fromIterable(doersList.getDoers())).cast(Doer.class);
    }

    @Override
    public Mono<Void> deleteDoer(String id) {
        return null;
    }


    @Getter @Setter
    static class DoersList {
        private List<DoerJson> doers;
    }

}
