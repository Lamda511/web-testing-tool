package lamda511.webtestingtool.services;

import lamda511.webtestingtool.models.TestItem;
import lamda511.webtestingtool.models.TestItemDescription;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface CommandService {

    public boolean canProcessCommand(String commandType);

    public TestItem processCommand(WebDriver driver, TestItem command, Map<String, String> vars);

    public TestItem getCommandTemplate();

    public TestItemDescription getTestItemDescription();
}
