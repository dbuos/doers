package bid.dbo.doers.mongodb.tasks;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface TaskDataRepository extends ReactiveCrudRepository<TaskData, String>, ReactiveQueryByExampleExecutor<TaskData> {

}
