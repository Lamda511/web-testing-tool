package lamda511.webtestingtool.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TestItemRequest {

    @JsonProperty("test_item_command")
    private String testItemCommand;
}
