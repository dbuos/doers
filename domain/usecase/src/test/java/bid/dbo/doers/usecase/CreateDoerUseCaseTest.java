package bid.dbo.doers.usecase;

import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.error;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

@RunWith(MockitoJUnitRunner.class)
public class CreateDoerUseCaseTest {

    private CreateDoerUseCase useCase;

    @Mock
    private DoersRepository localRepo;

    @Mock
    private DoersRepository externalRepo;

    @Mock
    private DomainEventBus eventBus;

    @Before
    public void init() {
        useCase = new CreateDoerUseCase(externalRepo, localRepo, eventBus);
    }

    @Test
    public void createDoerShouldSaveInBothRepositories() {
        Doer doer = Doer.builder().name("Test Doer").build();

        PublisherProbe<Doer> localAction = PublisherProbe.empty();
        PublisherProbe<Doer> remoteAction = PublisherProbe.empty();

        when(externalRepo.save(any())).thenReturn(remoteAction.mono());
        when(localRepo.save(any())).thenReturn(localAction.mono());

        useCase.createDoer(doer).as(StepVerifier::create)
            .verifyComplete();

        localAction.assertWasSubscribed();
        remoteAction.assertWasSubscribed();
    }

    @Test
    public void createDoerShouldContinueOnRemoteError() throws InterruptedException {
        final Doer doer = Doer.builder().name("Test Doer").build();

        final PublisherProbe<Doer> localAction = PublisherProbe.empty();
        final PublisherProbe<Void> eventAction = PublisherProbe.empty();

        when(externalRepo.save(any())).thenReturn(error(new RuntimeException()));
        when(localRepo.save(any())).thenReturn(localAction.mono());
        when(eventBus.emit(any())).thenReturn(eventAction.mono());

        useCase.createDoer(doer).as(StepVerifier::create)
            .verifyComplete();

        localAction.assertWasSubscribed();
        eventAction.assertWasSubscribed();
    }
}