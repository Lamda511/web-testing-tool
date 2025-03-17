package lamda511.webtestingtool.services;

import lamda511.webtestingtool.entities.TestEntity;
import lamda511.webtestingtool.models.RunStatuses;
import lamda511.webtestingtool.models.Test;
import lamda511.webtestingtool.models.TestItem;
import lamda511.webtestingtool.repositories.TestsRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class TestRunner {

    private final ServiceLoader<CommandService> loader;

    private final TestsRepository testsRepository;

    @Autowired
    public TestRunner(ServiceLoader<CommandService> loader,
                      TestsRepository testsRepository) {
        this.loader = loader;
        this.testsRepository = testsRepository;
    }

    public Test runTest(Test test) throws Exception {
        test.setTestResult(RunStatuses.RUNNING.getValue());

        List<RunStatuses> collectedRunStatuses = new ArrayList<>();

        WebDriver driver = new ChromeDriver();

        Map<String, String> globalVars = test.getGlobalVariables();
        List<TestItem> testItems = test.getTestItems();

        for(TestItem testItem : testItems) {
            testItem.setRunStatus(RunStatuses.RUNNING.getValue());
            Optional<CommandService> firstTestItem = StreamSupport.stream(loader.spliterator(), false)
                    .filter(item -> item.canProcessCommand(testItem.getCommand())).findFirst();

            if (firstTestItem.isPresent()) {
                CommandService commandService = firstTestItem.get();
                commandService.processCommand(driver, testItem, globalVars);
                collectedRunStatuses.add(RunStatuses.fromName(testItem.getRunStatus()));
            } else {
                throw new Exception("Invalid command");
            }
        }

        Optional<RunStatuses> any = collectedRunStatuses.stream().filter(item -> item != RunStatuses.FINISHED_SUCCESS).findAny();
        test.setTestResult(any.isPresent() ? RunStatuses.FINISHED_ERROR.getValue() : RunStatuses.FINISHED_SUCCESS.getValue());

        return test;
    }

    public boolean shouldTestRun(Test test) {
        TestEntity testEntity = testsRepository.findByTestId(test.getTestId());
        String status = testEntity.getStatus();

        return RunStatuses.fromName(status) != RunStatuses.STOPPED;
    }

    public String stopTest(Test test) {
        try {
            TestEntity testEntity = testsRepository.findByTestId(test.getTestId());
            testEntity.setStatus(RunStatuses.STOPPED.getValue());
            TestEntity savedTestRunsEntity = testsRepository.save(testEntity);
            return savedTestRunsEntity.getId();
        } catch (Exception ex) {
            //TODO: log error or return it.
            return "0";
        }
    }
}
