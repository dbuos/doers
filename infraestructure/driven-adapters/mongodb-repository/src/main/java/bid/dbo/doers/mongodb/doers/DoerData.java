package bid.dbo.doers.mongodb.doers;

import bid.dbo.doers.domain.doers.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Map;

@Data
@Document
@NoArgsConstructor
public class DoerData {
    @Id
    private String id;
    private String name;
    private Collection<Skill> skills;
    private Collection<String> travelRadius;
    private Collection<Map<String, String>> others;
}
