package bid.dbo.doers.repository.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;

class JSONObjectMapper {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER = mapper;
    }

    static <T> T read(JsonNode json, TypeReference<T> clazz) {
        try {
            final String text = json.asText();
            return text.isEmpty() ? null : OBJECT_MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
