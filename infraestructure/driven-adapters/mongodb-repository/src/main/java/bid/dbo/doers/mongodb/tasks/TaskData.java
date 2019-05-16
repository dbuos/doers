package bid.dbo.doers.mongodb.tasks;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
public class TaskData {
    @Id
    private String id;
    private String skill;
    private Double proposedPrice;
    private String clientID;
    private Date scheduledDate;
}
