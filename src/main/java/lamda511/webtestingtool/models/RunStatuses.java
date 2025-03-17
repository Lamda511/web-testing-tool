package lamda511.webtestingtool.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public enum RunStatuses {

    UNKNOWN("unknown"),
    NOT_RUN("not_run"),
    RUNNING("running"),
    STOPPED("stopped"),
    FINISHED_SUCCESS("finished_success"),
    FINISHED_ERROR("finished_error");

    private final String value;

    public static RunStatuses fromName(String statusName) {
        Optional<RunStatuses> runStatus = Arrays.stream(values())
                .filter(status -> StringUtils.equalsIgnoreCase(statusName, status.getValue()))
                .findFirst();

        return runStatus.orElse(UNKNOWN);
    }
}
