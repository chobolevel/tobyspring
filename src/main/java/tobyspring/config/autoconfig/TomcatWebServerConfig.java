package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {

    @Bean("tomcatWebServerConfig")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(serverProperties.getContextPath());
        factory.setPort(serverProperties.getPort());

        return factory;
    }

//    Environment를 이용해서 환경변수를 가져오는 방법
//    @Bean("tomcatWebServerConfig")
//    @ConditionalOnMissingBean
//    public ServletWebServerFactory servletWebServerFactory(Environment env) {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.setContextPath(env.getProperty("contextPath"));
//        return factory;
//    }


//    Tomcat클래스가 존재하는지 확인하여 boolean을 반환하는 Condition 클래스
//    static class TomcatCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
//        }
//    }

}
