package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.tasks.Task;
import bid.dbo.doers.domain.events.TaskSubmitted;
import org.junit.Test;
import reactor.test.StepVerifier;

public class MatchDoerTaskUseCaseTest {

    private MatchDoerTaskUseCase useCase = new MatchDoerTaskUseCase();

    @Test
    public void matchDoersWithTask() {
        useCase.matchDoersWithTask(new TaskSubmitted("1", Task.builder().build()))
            .as(StepVerifier::create)
            .verifyComplete();
    }
}