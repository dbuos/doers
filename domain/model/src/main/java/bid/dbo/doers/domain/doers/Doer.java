package bid.dbo.doers.domain.doers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Doer {
    private String id;
    private String name;
    private Collection<Skill> skills;
    private Collection<String> travelRadius;
    private Collection<Map<String, String>> others;
}
