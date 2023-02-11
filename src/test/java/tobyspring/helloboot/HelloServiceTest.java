package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String res = helloService.sayHello("Test");

        Assertions.assertThat(res).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String res = helloDecorator.sayHello("Test");

        Assertions.assertThat(res).isEqualTo("*Test*");
    }

}
