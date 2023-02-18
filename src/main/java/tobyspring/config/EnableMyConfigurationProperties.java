package tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyConfigurationPropertiesImportSelector.class)
public @interface EnableMyConfigurationProperties {
    // 프로퍼티 클래스도 동적으로 가져오기 위해 만들어진 어노테이션
    Class<?> value();
}
