package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {

    @Test
    void conditional() {

//        AnnotationConfigWebApplicationContext ac1 = new AnnotationConfigWebApplicationContext();
//        ac1.register(Config1.class);
//        ac1.refresh();
//
//        MyBean bean1 = ac1.getBean(MyBean.class);
//        // false
//        AnnotationConfigWebApplicationContext ac2 = new AnnotationConfigWebApplicationContext();
//        ac2.register(Config2.class);
//        ac2.refresh();
//
//        MyBean bean2 = ac2.getBean(MyBean.class);


        // true
        ApplicationContextRunner contextRunner1 = new ApplicationContextRunner();
        contextRunner1.withUserConfiguration(Config1.class)
                .run(context -> {
                    // 빈팩토리 메서드를 통해 빈이 등록되었는지 확인
                    Assertions.assertThat(context).hasSingleBean(MyBean.class);
                    // 던져준 Configuration 클래스도 빈으로 등록되었는지 확인
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });

        // false (아래처럼 변수에 담지 않고도 가능함)
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                   Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
                   Assertions.assertThat(context).doesNotHaveBean(Config2.class);
                });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(FalseCondition.class)
//    @interface FalseConditional {}

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean) annotationAttributes.get("value");
            return value;
        }
    }

//    static class TrueCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return true;
//        }
//    }

//    static class FalseCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return false;
//        }
//    }

}
