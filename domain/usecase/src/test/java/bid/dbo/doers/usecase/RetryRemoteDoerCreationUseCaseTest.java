package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetryRemoteDoerCreationUseCaseTest {

    @Mock
    private DoersRepository externalRepo;

    @InjectMocks
    private RetryRemoteDoerCreationUseCase useCase;

    @Test
    public void retryCreateDoer() {
        final Doer doer = Doer.builder().name("Test").build();
        when(externalRepo.save(doer)).thenReturn(Mono.empty());
        useCase.retryCreateDoer(doer)
            .as(StepVerifier::create)
            .verifyComplete();
    }
}