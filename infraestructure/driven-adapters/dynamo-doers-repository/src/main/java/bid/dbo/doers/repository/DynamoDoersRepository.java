package bid.dbo.doers.repository;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import bid.dbo.doers.domain.doers.Skill;
import bid.dbo.doers.repository.json.DoerJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static reactor.core.publisher.Flux.fromIterable;
import static reactor.core.publisher.Flux.just;

@RequiredArgsConstructor
@Component
public class DynamoDoersRepository implements DoersRepository {

    private final WebClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Flux<Doer> findAll() {
        return client.get().uri("/doers")
            .retrieve().bodyToMono(DoersList.class)
            .flatMapMany(doersList -> fromIterable(doersList.getDoers())).cast(Doer.class);
    }

    @Override
    public Mono<Doer> findById(String id) {
        return client.get()
            .uri("/doers/" + id)
            .retrieve().bodyToMono(DoerJson.class)
            .cast(Doer.class);
    }

    @Override
    public Flux<Doer> searchBySkill(String skillName) {
        return client.get().uri("/skills?skill="+skillName)
            .retrieve().bodyToMono(DoersList.class)
            .flatMapMany(doersList -> fromIterable(doersList.getDoers())).cast(Doer.class);
    }

    @Override
    public Mono<Void> deleteDoer(String id) {
        return client.delete()
            .uri("/doers/" + id)
            .exchange().then();
    }

    @Override
    public Mono<Doer> save(Doer doer) {
        try {
            DoerToJson toJson = new DoerToJson(doer.getId(),
                doer.getName(), mapper.writeValueAsString(doer.getSkills()), doer.getTravelRadius(), doer.getOthers());
            return client.post().uri("/doers").syncBody(toJson).exchange()
                .flatMap(resp -> resp.statusCode().isError() ?
                    Mono.error(new RuntimeException(resp.statusCode().getReasonPhrase()))
                    : Mono.just(doer));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Getter @Setter
    static class DoersList {
        private List<DoerJson> doers;
    }

    @Data
    @AllArgsConstructor
    private static class DoerToJson  {
        private String id;
        private String name;
        private String skills;
        private Collection<String> travelRadius;
        private Collection<Map<String, String>> others;
    }


}
