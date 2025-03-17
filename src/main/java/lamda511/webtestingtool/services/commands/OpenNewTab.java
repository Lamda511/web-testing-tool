package lamda511.webtestingtool.services.commands;

import com.google.auto.service.AutoService;

import lamda511.webtestingtool.models.RunStatuses;
import lamda511.webtestingtool.models.TestItem;
import lamda511.webtestingtool.models.TestItemDescription;
import lamda511.webtestingtool.services.CommandService;
import lamda511.webtestingtool.util.VarsUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@AutoService(CommandService.class)
@Component
public class OpenNewTab implements CommandService {

    public static final String COMMAND_ID = "open_new_tab";
    public static final String COMMAND_NAME = "Open New Tab";
    public static final String COMMAND_DESCRIPTION = "Open a new tab and switch to it";
    public static final String OLD_TAB_ID = "old_tab_id";
    public static final String NEW_TAB_ID = "new_tab_id";


    public OpenNewTab() {
    }

    @Override
    public boolean canProcessCommand(String commandId) {
        return StringUtils.isNotBlank(commandId) && StringUtils.equalsIgnoreCase(commandId, COMMAND_ID);
    }

    @Override
    public TestItem processCommand(WebDriver driver, TestItem command, Map<String, String> vars) {
        try {
            String oldWindowHandle = driver.getWindowHandle();
            driver.switchTo().newWindow(WindowType.TAB);
            String newWindowHandle = driver.getWindowHandle();

            Map<String, String> outVars = command.getOutVars();
            String oldTabIdVar = outVars.get(OLD_TAB_ID);
            String newTabIdVar = outVars.get(NEW_TAB_ID);

            if (StringUtils.isNotBlank(oldTabIdVar)) {
                VarsUtil.resolveSetVarValue(oldTabIdVar, oldWindowHandle, vars);
            }

            if (StringUtils.isNotBlank(newTabIdVar)) {
                VarsUtil.resolveSetVarValue(newTabIdVar, newWindowHandle, vars);
            }

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
        command.setInVars(new HashMap<>());

        Map<String, String> outVars = new HashMap<>();
        outVars.put(OLD_TAB_ID, "");
        outVars.put(NEW_TAB_ID, "");
        command.setOutVars(outVars);
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
