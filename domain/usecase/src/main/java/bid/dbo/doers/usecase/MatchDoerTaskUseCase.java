package bid.dbo.doers.usecase;

import bid.dbo.doers.domain.events.TaskSubmitted;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

@Log
public class MatchDoerTaskUseCase {

    public Mono<Void> matchDoersWithTask(TaskSubmitted submitted) {
        log.info("Matching process...");
        return Mono.just(submitted).log().then();
    }

}
