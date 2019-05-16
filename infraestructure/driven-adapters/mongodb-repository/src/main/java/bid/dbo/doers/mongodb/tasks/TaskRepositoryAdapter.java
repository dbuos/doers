package bid.dbo.doers.mongodb.tasks;

import bid.dbo.doers.domain.tasks.Task;
import bid.dbo.doers.domain.tasks.TaskRepository;
import bid.dbo.doers.repository.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryAdapter extends AdapterOperations<Task, TaskData, String, TaskDataRepository> implements TaskRepository {

    @Autowired
    public TaskRepositoryAdapter(TaskDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Task.TaskBuilder.class).build());
    }

}
