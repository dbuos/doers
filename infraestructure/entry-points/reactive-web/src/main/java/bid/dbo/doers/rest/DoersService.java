package bid.dbo.doers.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DoersService {

    @GetMapping(path = "doers", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> get() {
        return Mono.just("OK");
    }

}
