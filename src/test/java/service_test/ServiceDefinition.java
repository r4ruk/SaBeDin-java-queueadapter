package service_test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServiceDefinition {
    private String name;

    @JsonProperty("query_methods")
    private List<MethodDefinition> queryMethods;

    @JsonProperty("command_methods")
    private List<MethodDefinition> commandMethods;

}
