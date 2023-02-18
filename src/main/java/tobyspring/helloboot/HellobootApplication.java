package tobyspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {

//	스프링부트 어플리케이션 구동 후 간단한 코드를 테스트하기 위한 Bean
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
