package lamda511.webtestingtool;

import lamda511.webtestingtool.services.CommandService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ServiceLoader;

@SpringBootApplication
public class WebTestingToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebTestingToolApplication.class, args);
	}


	@Bean
	public ServiceLoader<CommandService> loadServices() {
		return ServiceLoader.load(CommandService.class);
	}

}
