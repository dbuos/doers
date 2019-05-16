package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.tasks.Task;
import bid.dbo.doers.domain.tasks.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubmitTaskUseCaseTest {

    @InjectMocks
    private SubmitTaskUseCase useCase;

    @Mock
    private DomainEventBus eventBus;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void submitTask() {
        Task task = Task.builder().skill("Pasear Perro").build();
        when(eventBus.emit(any())).thenReturn(Mono.empty());
        when(taskRepository.save(any())).thenReturn(Mono.empty());
        useCase.submitTask(task)
            .as(StepVerifier::create)
            .verifyComplete();
    }
}