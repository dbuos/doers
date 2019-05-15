package bid.dbo.doers.repository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import bid.dbo.doers.domain.Doer;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class DynamoDoersRepositoryTest {

    DynamoDoersRepository repository = new DynamoDoersRepository(WebClient.builder()
        .baseUrl("https://534vc3yeye.execute-api.us-east-1.amazonaws.com/dev").build());

    @Test
    public void findById() {
        repository.findById("2")
            .as(StepVerifier::create)
            .assertNext(doer -> {
                System.out.println(doer);
                assertThat(doer.getName()).contains("Nombre");
            }).verifyComplete();
    }

    @Test
    public void searchTest() {
        repository.searchBySkill("CAPA")
            .as(StepVerifier::create)
            .assertNext(doer -> {
                System.out.println(doer);
                assertThat(doer.getSkills().iterator().next().getSkill()).contains("CAP");
            }).expectNextCount(1).verifyComplete();
    }
}