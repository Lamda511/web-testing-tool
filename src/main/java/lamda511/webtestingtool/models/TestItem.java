package lamda511.webtestingtool.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TestItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("in_vars")
    private Map<String, String> inVars;

    @JsonProperty("out_vars")
    private Map<String, String> outVars;

    @JsonProperty("command")
    private String command;

    @JsonProperty("run_status")
    private String runStatus;

    @JsonProperty("result_message")
    private String resultMessage;
}
