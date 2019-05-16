package bid.dbo.doers.mongodb.doers;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface DoerDataRepository extends ReactiveCrudRepository<DoerData, String>, ReactiveQueryByExampleExecutor<DoerData> {

}
