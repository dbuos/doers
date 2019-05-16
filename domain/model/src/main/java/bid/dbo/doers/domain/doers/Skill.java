package bid.dbo.doers.domain.doers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    private String skill;
    private Double value;
    private Double margin;

}
