package bid.dbo.doers.rest;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.usecase.CreateDoerUseCase;
import bid.dbo.doers.usecase.query.QueryDoersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DoersService {

    @Autowired
    private QueryDoersUseCase queryDoersUseCase;

    @Autowired
    private CreateDoerUseCase createDoerUseCase;


    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> get() {
        return Mono.just("OK");
    }

    @PostMapping(path = "/doer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> createDoer(Doer doer) {
        return createDoerUseCase.createDoer(doer);
    }

    @GetMapping(path = "/doer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Doer> getAll() {
        return queryDoersUseCase.findAll();
    }

}
