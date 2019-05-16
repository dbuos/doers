package bid.dbo.doers.usecase.query;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryDoersUseCaseTest {

    private final Doer doer1 = Doer.builder().name("Pedro F").build();
    private final Doer doer2 = Doer.builder().name("Jose A").build();
    private final Doer doer3 = Doer.builder().name("Daniel B").build();
    private QueryDoersUseCase useCase;
    @Mock
    private DoersRepository doers;
    @Mock
    private DoersRepository doersFallback;

    @Test
    public void shouldFindAllFromDoersRepo() {
        when(doers.findAll()).thenReturn(Flux.just(doer1, doer2, doer3));
        useCase = new QueryDoersUseCase(doers, doersFallback);
        useCase.findAll().as(StepVerifier::create)
            .expectNext(doer1, doer2, doer3)
            .verifyComplete();
    }

    @Test
    public void shouldFindAllWhenRemoteRepoFails() {
        when(doers.findAll()).thenReturn(Flux.error(new RuntimeException()));
        when(doersFallback.findAll()).thenReturn(Flux.just(doer1, doer3));
        useCase = new QueryDoersUseCase(doers, doersFallback);
        useCase.findAll().as(StepVerifier::create)
            .expectNext(doer1, doer3)
            .verifyComplete();
    }

    @Test
    public void shouldFindAllWhenRemoteRepoTimeOut() {
        when(doers.findAll()).thenReturn(Flux.just(doer1, doer2, doer3).delaySubscription(ofSeconds(20)));
        when(doersFallback.findAll()).thenReturn(Flux.just(doer1, doer2));
        useCase = new QueryDoersUseCase(doers, doersFallback);
        useCase.findAll().as(StepVerifier::create)
            .expectNext(doer1, doer2)
            .verifyComplete();
    }

    @Test
    public void shouldFindAllEmptyWhenError() {
        when(doers.findAll()).thenReturn(Flux.error(new RuntimeException()));
        when(doersFallback.findAll()).thenReturn(Flux.error(new RuntimeException()));
        useCase = new QueryDoersUseCase(doers, doersFallback);
        useCase.findAll().as(StepVerifier::create)
            .verifyComplete();
    }

    @Test
    public void shouldFindAllUsingCache() {
        final AtomicInteger counter = new AtomicInteger();
        when(doers.findAll()).then(invocation -> {
            counter.incrementAndGet();
            return Flux.just(doer1, doer2, doer3);
        });
        useCase = new QueryDoersUseCase(doers, doersFallback);

        useCase.findAll().collectList().block();
        useCase.findAll().collectList().block();
        useCase.findAll().collectList().block();
        useCase.findAll().collectList().block();

        assertThat(counter).hasValue(1);
    }
}