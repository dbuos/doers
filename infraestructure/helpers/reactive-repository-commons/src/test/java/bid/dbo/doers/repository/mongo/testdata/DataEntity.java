package bid.dbo.doers.repository.mongo.testdata;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
class DataEntity {
    @Id
    private String id;
    private String name;
    private Date birthDate;
    private Long size;
}
