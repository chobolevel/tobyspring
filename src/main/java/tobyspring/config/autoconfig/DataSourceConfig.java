package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
// 아래 어노테이션으로 이 클래스가 빈으로 등록됟 때 MyDataSourceProperties클래스도 빈으로 등록
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {


    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    public DataSource hikariDataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }
    @Bean
    // 앞선 빈초기화 과정에서 DataSource타입의 빈이 없는 경우 DataSource타입 빈 생성
    @ConditionalOnMissingBean
    public DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
       SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

       dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
       dataSource.setUrl(properties.getUrl());
       dataSource.setUsername(properties.getUsername());
       dataSource.setPassword(properties.getPassword());

       return dataSource;
    }

    @Bean
    // DataSource타입의 빈이 단 하나만 있을 경우 해당 빈을 가져와서 사용하는 conditional
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    public JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
