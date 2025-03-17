package lamda511.webtestingtool.services.commands;

import com.google.auto.service.AutoService;
import lamda511.webtestingtool.models.RunStatuses;
import lamda511.webtestingtool.models.TestItem;
import lamda511.webtestingtool.models.TestItemDescription;
import lamda511.webtestingtool.services.CommandService;
import lamda511.webtestingtool.util.VarsUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@AutoService(CommandService.class)
public class GoToUrl implements CommandService {

    public static final String COMMAND_ID = "go_to_url";
    public static final String COMMAND_NAME = "Go To URL";
    public static final String COMMAND_DESCRIPTION = "Go To URL";
    public static final String URL_STRING = "url";


    public GoToUrl() {
    }

    @Override
    public boolean canProcessCommand(String commandId) {
        return StringUtils.isNotBlank(commandId) && StringUtils.equalsIgnoreCase(commandId, COMMAND_ID);
    }

    @Override
    public TestItem processCommand(WebDriver driver, TestItem command, Map<String, String> vars) {
        try {
            Map<String, String> inVars = command.getInVars();
            String urlString = VarsUtil.resolveGetVarValue(inVars.get(URL_STRING), vars);
            driver.get(urlString);
            command.setRunStatus(RunStatuses.FINISHED_SUCCESS.getValue());
        } catch (Exception e) {
            command.setRunStatus(RunStatuses.FINISHED_ERROR.getValue());
            command.setResultMessage(e.getMessage());
        }

        return command;
    }

    @Override
    public TestItem getCommandTemplate() {
        TestItem command = new TestItem();
        command.setName(COMMAND_NAME);
        command.setDescription(COMMAND_DESCRIPTION);
        command.setCommand(COMMAND_ID);
        Map<String, String> inVars = new HashMap<>();
        inVars.put(URL_STRING, "");
        command.setInVars(inVars);
        command.setOutVars(new HashMap<>());
        command.setRunStatus(RunStatuses.NOT_RUN.getValue());
        command.setResultMessage("");

        return command;
    }

    @Override
    public TestItemDescription getTestItemDescription() {
        TestItemDescription commandDescription = new TestItemDescription();
        commandDescription.setName(COMMAND_NAME);
        commandDescription.setDescription(COMMAND_DESCRIPTION);
        commandDescription.setCommand(COMMAND_ID);

        return commandDescription;
    }


}
