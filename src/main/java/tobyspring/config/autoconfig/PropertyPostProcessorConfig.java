package tobyspring.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyConfigurationProperties;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {

    // 모든 빈들이 초기화될 때마다 아래의 빈을 거쳐서 빈 저장소에 등록되게 됨
    @Bean
    public BeanPostProcessor beanPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                // MyConfigurationProperties어노테이션이 없다면 아무런 후처리를 하지 않음
                if(annotation == null) return bean;

                // MyconfigurationProperties 어노테이션이 있다면, 바인더를 통해 properties를 바인딩한 빈을 반환
                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attrs.get("prefix");

                // bindOrCreate는 바인딩을 하지 못하면 해당 객체를 new를 통해 생성하여 반환
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }

}
