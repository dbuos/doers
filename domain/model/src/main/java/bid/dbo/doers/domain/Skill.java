package bid.dbo.doers.domain;

import lombok.Data;

@Data
public class Skill {
    private final String skill;
    private final Double value;
    private final Double margin;
}
