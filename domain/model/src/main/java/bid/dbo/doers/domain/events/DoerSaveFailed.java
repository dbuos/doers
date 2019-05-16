package bid.dbo.doers.domain.events;

import bid.dbo.doers.domain.doers.Doer;
import org.reactivecommons.api.domain.DomainEvent;

public class DoerSaveFailed extends DomainEvent<Doer> {

    public static final String EVENT_NAME = "doers.doer.save.failed";

    public DoerSaveFailed(String eventId, Doer data) {
        super(EVENT_NAME, eventId, data);
    }
}
