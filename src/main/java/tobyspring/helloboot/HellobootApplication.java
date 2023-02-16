package tobyspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {

//	@Bean
//	ApplicationRunner applicationRunner(Environment env) {
//		return args -> {
//			String property = env.getProperty("my.name");
//			System.out.println("my name : "+ property);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}

}
