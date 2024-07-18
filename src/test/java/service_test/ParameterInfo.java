package service_test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParameterInfo {
    @JsonProperty("param_name")
    private String paramName;

    @JsonProperty("param_type")
    private String paramType;
}
