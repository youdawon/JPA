package jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(name = "svcEntityManager")
    private EntityManager svcEntityManager;

    @Bean
    public JPAQueryFactory svcJpaQueryFactory() {
        return new JPAQueryFactory(svcEntityManager);
    }
}