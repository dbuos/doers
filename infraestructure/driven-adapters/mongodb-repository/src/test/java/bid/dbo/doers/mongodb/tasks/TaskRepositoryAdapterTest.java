package bid.dbo.doers.mongodb.tasks;

import bid.dbo.doers.domain.tasks.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryAdapterTest {

    private final Task task1 = Task.builder().id("1").clientID("2").proposedPrice(1.1).skill("Madera").build();
    private final Task task2 = Task.builder().id("2").clientID("3").proposedPrice(1.1).skill("Pintar").build();
    @Autowired
    private TaskRepositoryAdapter adapter;

    @Before
    public void saveInitialData() {
        final Mono<Task> result = adapter.save(task1).then(adapter.save(task2));
        StepVerifier.create(result).assertNext(task -> {}).verifyComplete();
    }

    @Test
    public void shouldFindById() {
        StepVerifier.create(adapter.findById("1"))
            .assertNext(tasks -> assertThat(tasks).isEqualTo(task1)).verifyComplete();
    }


}