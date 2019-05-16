package bid.dbo.doers;

import bid.dbo.doers.domain.doers.DoersRepository;
import bid.dbo.doers.domain.tasks.TaskRepository;
import bid.dbo.doers.usecase.CreateDoerUseCase;
import bid.dbo.doers.usecase.MatchDoerTaskUseCase;
import bid.dbo.doers.usecase.RetryRemoteDoerCreationUseCase;
import bid.dbo.doers.usecase.SubmitTaskUseCase;
import bid.dbo.doers.usecase.query.QueryDoersUseCase;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Autowired
    @Qualifier("remote")
    private DoersRepository doersRemote;

    @Autowired
    @Qualifier("local")
    private DoersRepository doersLocal;

    @Autowired
    private DomainEventBus domainEventBus;

    @Bean
    public CreateDoerUseCase createDoerUseCase() {
        return new CreateDoerUseCase(doersRemote, doersLocal, domainEventBus);
    }

    @Bean
    public QueryDoersUseCase queryDoersUseCase() {
        return new QueryDoersUseCase(doersRemote, doersLocal);
    }

    @Bean
    public SubmitTaskUseCase submitTaskUseCase(TaskRepository taskRepository) {
        return new SubmitTaskUseCase(taskRepository, domainEventBus);
    }

    @Bean
    public RetryRemoteDoerCreationUseCase retryRemoteDoerCreationUseCase() {
        return new RetryRemoteDoerCreationUseCase(doersRemote);
    }

    @Bean
    public MatchDoerTaskUseCase matchDoerTaskUseCase() {
        return new MatchDoerTaskUseCase();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

}
