package com.panpraz.informasisertifikasidanedukasicp.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.DemoProject")
    public DataSourceProperties  DemoProjectDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.notification")
    public DataSourceProperties  notificationDataProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource DemoProjectDataSource() {
        return this.DemoProjectDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public DataSource notificationDataSource() {
        return this.notificationDataProperties().initializeDataSourceBuilder().build();
    }

}
