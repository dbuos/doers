package bid.dbo.doers.repository.json;

import bid.dbo.doers.domain.doers.Doer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collection;
import java.util.Map;

import static bid.dbo.doers.repository.json.JSONObjectMapper.read;

@SuppressWarnings("unchecked")
public class DoerJson extends Doer {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DoerJson(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("skills") JsonNode skills,
                    @JsonProperty("travelRadius") Collection<String> travelRadius,
                    @JsonProperty("others") Collection<Map<String, String>> others) {
        super(id, name, (Collection) read(skills, new TypeReference<Collection<SkillJson>>() {}), travelRadius, others);
    }
}

