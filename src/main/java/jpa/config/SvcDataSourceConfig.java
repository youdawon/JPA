package jpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "svcEntityManagerFactory",
        transactionManagerRef = "svcTransactionManager",
        basePackages = { "jpa.database.auth.repository" }
)
public class SvcDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.auth")
    public DataSourceProperties svcDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.auth.configuration")
    public DataSource svcsDataSource(@Qualifier("svcDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean svcEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("svcsDataSource") DataSource dataSource) {
        Map<String, String> properties = new HashMap<>();

        /**
         * entity 컬럼 이름 camel에서 snake로 자동으로 읽도록 설정
         * 미설정 시 entity 내 파라미터에 이름 명시 필요
         */
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put("hibernate.show_sql", "false");

        return builder
                .dataSource(dataSource)
                .packages("jpa.database.auth.entity")
                .persistenceUnit("svcEntityManager")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager svcTransactionManager(@Qualifier("svcEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}