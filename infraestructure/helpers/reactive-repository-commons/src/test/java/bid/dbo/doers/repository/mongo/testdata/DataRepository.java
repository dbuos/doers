package bid.dbo.doers.repository.mongo.testdata;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface DataRepository extends ReactiveCrudRepository<DataEntity, String>, ReactiveQueryByExampleExecutor<DataEntity> {
}
