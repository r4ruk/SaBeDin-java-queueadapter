package sabedin;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
public class QueueRequestMessage {
    @JsonProperty("message_id")
    private UUID messageId;

    @JsonProperty("correlation_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String correlationId;

    @JsonProperty("headers")
    private String headers;

    @JsonProperty("body")
    private RequestPostBody body;
}