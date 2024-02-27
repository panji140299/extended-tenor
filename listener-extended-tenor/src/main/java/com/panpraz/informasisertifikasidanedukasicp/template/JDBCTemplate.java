package com.panpraz.informasisertifikasidanedukasicp.template;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCTemplate {
    @Bean
    public JdbcTemplate DemoProjectJdbcTemplate(@Qualifier("DemoProjectDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate notificationJdbcTemplate(@Qualifier("notificationDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
