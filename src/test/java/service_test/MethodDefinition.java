package service_test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MethodDefinition {
    @JsonProperty("function_name")
    private String functionName;

    private List<ParameterInfo> params;

    @JsonProperty("return_type")
    private String returnType;
}
