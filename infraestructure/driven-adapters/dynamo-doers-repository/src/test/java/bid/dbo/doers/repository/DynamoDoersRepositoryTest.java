package bid.dbo.doers.repository;

import static org.assertj.core.api.Assertions.*;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.Skill;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.util.Arrays;

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
    public void save() {
        Doer doer = Doer.builder().id("100").name("Daniel")
            .skills(Arrays.asList(Skill.builder().skill("Madera").value(1.2).margin(0.2).build()))
            .travelRadius(Arrays.asList("medellin", "envigado")).build();
        repository.save(doer).as(StepVerifier::create)
            .expectNextCount(1).verifyComplete();
    }

    @Test
    public void findAll() {
        repository.findAll().collectList().log()
            .as(StepVerifier::create)
            .assertNext(doers -> assertThat(doers).isNotNull())
            .verifyComplete();
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