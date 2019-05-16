package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.tasks.Task;
import bid.dbo.doers.domain.tasks.TaskRepository;
import bid.dbo.doers.domain.events.TaskSubmitted;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.core.publisher.Mono;

import static bid.dbo.doers.domain.common.UniqueIDGenerator.uuid;
import static reactor.core.publisher.Mono.when;

@RequiredArgsConstructor
public class SubmitTaskUseCase {

    private final TaskRepository tasks;
    private final DomainEventBus eventBus;

    public Mono<Void> submitTask(Task task) {
        return uuid().map(id -> task.toBuilder().id(id).build())
            .flatMap(newTask -> when(tasks.save(newTask), emitTaskCreated(newTask)));
    }

    private Mono<Void> emitTaskCreated(Task task) {
        return Mono.from(eventBus.emit(new TaskSubmitted(task.getId(), task)));
    }

}
