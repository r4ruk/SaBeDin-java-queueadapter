package sabedin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;


@Data
public class RequestPostBody {
    @JsonProperty("idempotency_key")
    private String idempotencyKey;

    @JsonProperty("object")
    private String object;

    @JsonProperty("method")
    private String method;

    @JsonProperty("params")
    private Map<String, Object> params;

    @JsonProperty("query_options")
    private QueryOptions queryOptions;


}