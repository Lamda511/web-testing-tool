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
public class Test {

    @JsonProperty("test_id")
    private String testId;

    @JsonProperty("test_name")
    private String testName;

    @JsonProperty("test_description")
    private String testDescription;

    @JsonProperty("test_global_vars")
    private Map<String, String> globalVariables;

    @JsonProperty("test_items")
    private List<TestItem> testItems;

    @JsonProperty("test_result")
    private String testResult;
}
