package bid.dbo.doers.repository.mongo;

import bid.dbo.doers.repository.mongo.testdata.DomainEntity;
import bid.dbo.doers.repository.mongo.testdata.EntityRepositoryAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdapterOperationsTest {

    private final DomainEntity entity = DomainEntity.builder().id("01").name("My Name").size(42L).birthDate(new Date()).build();
    private final DomainEntity entity2 = DomainEntity.builder().id("02").name("My Name").size(43L).birthDate(new Date()).build();
    private final DomainEntity entity3 = DomainEntity.builder().id("03").name("My Name").size(43L).birthDate(new Date()).build();
    private final DomainEntity entity4 = DomainEntity.builder().id("04").name("My Name").size(44L).birthDate(new Date()).build();
    @Autowired
    private EntityRepositoryAdapter adapter;

    @Before
    public void init() {
        StepVerifier.create(adapter.save(entity)).expectNextCount(1).verifyComplete();
        StepVerifier.create(adapter.save(entity2)).expectNextCount(1).verifyComplete();
        StepVerifier.create(adapter.save(entity3)).expectNextCount(1).verifyComplete();
        StepVerifier.create(adapter.save(entity4)).expectNextCount(1).verifyComplete();
    }

    @Test
    public void shouldSaveAndFind() {
        final Mono<DomainEntity> saved = adapter.save(entity);

        StepVerifier.create(saved).expectNext(entity).verifyComplete();
        StepVerifier.create(adapter.findById("01")).expectNext(entity).verifyComplete();
    }


    @Test
    public void findByExample() {
        final Flux<DomainEntity> byExample = adapter.findByExample(DomainEntity.builder().size(43L).build());
        StepVerifier.create(byExample.collectList())
            .assertNext(list -> assertThat(list).containsOnly(entity2, entity3))
            .verifyComplete();

    }
}