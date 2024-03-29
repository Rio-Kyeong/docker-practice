package practice.docker.core.util.logging.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document("request_logs")
public class RequestDocument extends BaseDocument {

    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String requestId;

    private String url;

    private String method;

    private Object header;

    private Object body;
}
