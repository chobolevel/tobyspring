package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.config.MySpringBootApplication;

import javax.annotation.PostConstruct;

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

	private final JdbcTemplate jdbcTemplate;

	public HellobootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}

}
