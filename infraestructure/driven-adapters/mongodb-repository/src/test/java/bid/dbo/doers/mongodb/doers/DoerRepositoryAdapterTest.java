package bid.dbo.doers.mongodb.doers;

import static org.assertj.core.api.Assertions.*;
import static reactor.core.publisher.Flux.just;

import bid.dbo.doers.domain.doers.Doer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoerRepositoryAdapterTest {

    private final Doer doer1 = Doer.builder().name("Nombre 1").id("1").build();
    private final Doer doer2 = Doer.builder().name("Nombre 2").id("2").build();
    private final Doer doer3 = Doer.builder().name("Nombre 3").id("3").build();
    @Autowired
    private DoerRepositoryAdapter adapter;

    @Before
    public void saveInitialData() {
        final Mono<Doer> result = adapter.save(doer1).then(adapter.save(doer2)).then(adapter.save(doer3));
        StepVerifier.create(result).assertNext(doer -> {}).verifyComplete();
    }

    @Test
    public void shouldFindAll() {
        StepVerifier.create(adapter.findAll().collectList())
            .assertNext(tasks -> assertThat(tasks).contains(doer1, doer2, doer3)).verifyComplete();
    }

    @Test
    public void deleteDoer() {
        adapter.deleteDoer("1").as(StepVerifier::create).verifyComplete();
        StepVerifier.create(adapter.findAll().collectList())
            .assertNext(tasks -> assertThat(tasks).containsOnly(doer2, doer3)).verifyComplete();
        adapter.save(doer1).as(StepVerifier::create).assertNext(doer -> {}).verifyComplete();
    }

    @Test
    public void searchBySkill() {
        adapter.searchBySkill("skill").as(StepVerifier::create).verifyComplete();
    }


}