package bid.dbo.doers.domain.events;

import bid.dbo.doers.domain.tasks.Task;
import org.reactivecommons.api.domain.DomainEvent;

public class TaskSubmitted extends DomainEvent<Task> {

    public static final String EVENT_NAME = "doers.task.submitted";

    public TaskSubmitted(String eventId, Task data) {
        super(EVENT_NAME, eventId, data);
    }
}
