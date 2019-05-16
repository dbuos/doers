package bid.dbo.doers.mongodb.doers;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import bid.dbo.doers.repository.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Qualifier("local")
public class DoerRepositoryAdapter extends AdapterOperations<Doer, DoerData, String, DoerDataRepository> implements DoersRepository {

    @Autowired
    public DoerRepositoryAdapter(DoerDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Doer.DoerBuilder.class).build());
    }

    @Override
    public Flux<Doer> findAll() {
        return doQueryMany(repository.findAll());
    }

    @Override
    public Flux<Doer> searchBySkill(String skillName) {
        //To implement...
        return Flux.empty();
    }

    @Override
    public Mono<Void> deleteDoer(String id) {
        return repository.deleteById(id);
    }
}
