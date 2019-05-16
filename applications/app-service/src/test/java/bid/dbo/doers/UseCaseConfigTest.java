package bid.dbo.doers;

import org.junit.Test;

public class UseCaseConfigTest {

    private UseCaseConfig config = new UseCaseConfig();

    @Test
    public void createDoerUseCase() {
        config.createDoerUseCase();
    }


    @Test
    public void submitTaskUseCase() {
        config.submitTaskUseCase(null);
    }

    @Test
    public void retryRemoteDoerCreationUseCase() {
        config.retryRemoteDoerCreationUseCase();
    }

    @Test
    public void matchDoerTaskUseCase() {
        config.matchDoerTaskUseCase();
    }

    @Test
    public void objectMapper() {
        config.objectMapper();
    }
}