package bid.dbo.doers.domain.clients;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Client {
    private String id;
    private String name;
}
