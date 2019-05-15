package bid.dbo.doers.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoersRepository {

    Flux<Doer> findAll();
    Mono<Doer> findById(String id);
    Flux<Doer> searchBySkill(String skillName);
    Mono<Void> deleteDoer(String id);
}
