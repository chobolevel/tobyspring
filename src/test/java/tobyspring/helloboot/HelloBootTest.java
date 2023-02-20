package tobyspring.helloboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 스프링 컨테이너를 이용한 테스트 가능하도록 하는 어노테이션
@ExtendWith(SpringExtension.class)
// 모든 빈 구성정보를 테스트를 위해 가져올 수 있음
@ContextConfiguration(classes = HellobootApplication.class)
// properties작성 내용을 가져오지 못하기 때문에 가져오는 어노테이션 추가
@TestPropertySource("classpath:/application.properties")
// 테스트가 끝나고 다시 롤백 시키기 위해 사용됨
@Transactional
public @interface HelloBootTest {
}
