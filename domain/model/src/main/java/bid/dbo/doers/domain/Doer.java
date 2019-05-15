package bid.dbo.doers.domain;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class Doer {
    private final String id;
    private final String name;
    private final Collection<Skill> skills;
    private final Collection<String> travelRadius;
    private final Collection<Map<String, String>> others;
}
