package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService(helloRepository);
        String res = helloService.sayHello("Test");

        Assertions.assertThat(res).isEqualTo("Hello Test");
    }
    
    // 변수에 넣어 사용함으로서 몇번을 사용해도 객체 재생성을 방지
    private static HelloRepository helloRepository = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String res = helloDecorator.sayHello("Test");

        Assertions.assertThat(res).isEqualTo("*Test*");
    }

}
