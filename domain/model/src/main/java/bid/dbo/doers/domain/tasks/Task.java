package bid.dbo.doers.domain.tasks;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Task {
    private String id;
    private String skill;
    private Double proposedPrice;
    private String clientID;
    private Date scheduledDate;

}
