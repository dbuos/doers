package bid.dbo.doers.repository.json;

import bid.dbo.doers.domain.Skill;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class SkillJson extends Skill {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SkillJson(@JsonProperty("skill") String skill,
                     @JsonProperty("value") Double value,
                     @JsonProperty("margin") Double margin) {
        super(skill, value, margin);
    }
}
