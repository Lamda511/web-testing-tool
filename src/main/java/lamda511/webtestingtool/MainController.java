package lamda511.webtestingtool;

import lamda511.webtestingtool.models.Test;
import lamda511.webtestingtool.models.TestItem;
import lamda511.webtestingtool.models.TestItemDescription;
import lamda511.webtestingtool.models.TestItemRequest;
import lamda511.webtestingtool.services.CommandService;
import lamda511.webtestingtool.services.TestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

@RestController
public class MainController {

    private ServiceLoader<CommandService> loader;

    private TestRunner testRunner;

    @Autowired
    public MainController(ServiceLoader<CommandService> loader,
                          TestRunner testRunner) {
        this.loader = loader;
        this.testRunner = testRunner;
    }

    @PostMapping("/save-test")
    public String saveTest() {
        return null;
    }

    @PostMapping("/load-test")
    public String loadTest() {
        return null;
    }

    @PostMapping("/get-test-items-descriptions")
    public List<TestItemDescription> getTestItemsDescriptions() {
        return StreamSupport.stream(loader.spliterator(), false)
                .map(CommandService::getTestItemDescription).toList();
    }

    @PostMapping("/get-test-item-template")
    public TestItem getTestItemTemplate(@RequestBody TestItemRequest testItemRequest) {
        return StreamSupport.stream(loader.spliterator(), false)
                .filter(item -> item.canProcessCommand(testItemRequest.getTestItemCommand()))
                .map(CommandService::getCommandTemplate)
                .findFirst().orElse(null);
    }

    @PostMapping("/save-test-item")
    public String saveTestItem() {
        return null;
    }

    @PostMapping("/run-test")
    public Test runTest(@RequestBody Test test) throws Exception {
        return testRunner.runTest(test);
    }

    @PostMapping("/stop_running")
    public String stopRunning(@RequestBody Test test) {
        return testRunner.stopTest(test);
    }
}
