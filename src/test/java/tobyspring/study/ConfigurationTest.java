package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ConfigurationTest {

    @Test
    void configuration() {
        // 성공
        // Common common = new Common();
        // Assertions.assertThat(common).isSameAs(common);

        // 실패
        // Assertions.assertThat(new Common()).isSameAs(new Common());

        // 실패
        // MyConfig myConfig = new MyConfig();
        // Bean1 bean1 = myConfig.bean1();
        // Bean2 bean2 = myConfig.bean2();
        // Assertions.assertThat(bean1.common).isSameAs(bean2.common);

        // 하지만 Spring Container에 구성정보로 MyConfig가 들어가면 작동 방식이 달라짐
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    // proxyBeanMethod = true를 흉내가능함
    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if(this.common == null ) this.common = super.common();
            return this.common;
        }
    }

    @Configuration
    static class MyConfig {

        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }

    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }

    // Bean1 <-- Common
    // Bean2 <-- Common

}
