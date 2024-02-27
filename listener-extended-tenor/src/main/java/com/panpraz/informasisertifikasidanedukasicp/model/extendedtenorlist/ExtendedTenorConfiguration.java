package com.panpraz.informasisertifikasidanedukasicp.model.extendedtenorlist;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = ExtendedTenorRepository.class,
        entityManagerFactoryRef = "dataKonsumenEntityManagerFactory",
        transactionManagerRef = "dataKonsumenTransactionManager"
)
public class ExtendedTenorConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean dataKonsumenEntityManagerFactory(@Qualifier("DemoProjectDataSource") DataSource dataSource,
                                                                                   EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(ExtendedTenorList.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager dataKonsumenTransactionManager(
            @Qualifier("dataKonsumenEntityManagerFactory") LocalContainerEntityManagerFactoryBean dataKonsumenEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(dataKonsumenEntityManagerFactory.getObject()));
    }
}
