package bid.dbo.doers.usecase.query;

import bid.dbo.doers.domain.doers.Doer;
import bid.dbo.doers.domain.doers.DoersRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

import static java.time.Duration.ofSeconds;

public class QueryDoersUseCase {

    private final Flux<Doer> allDoers;

    public QueryDoersUseCase(DoersRepository doers, DoersRepository doersFallback) {
        this.allDoers = doers.findAll().timeout(ofSeconds(4))
            .log("QueryRemote", Level.WARNING, SignalType.ON_ERROR)
            .onErrorResume(t -> doersFallback.findAll())
            .log("QueryLocal", Level.WARNING, SignalType.ON_ERROR)
            .onErrorResume(t -> Flux.empty())
            .cache(ofSeconds(7));
    }

    public Flux<Doer> findAll() {
        return allDoers;
    }

}
