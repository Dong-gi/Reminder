package io.github.donggi.reminder.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("io.github.donggi.reminder.mapper")
public class DBConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean f = new SqlSessionFactoryBean();
        f.setDataSource(dataSource());
        //f.setConfigLocation(context.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
        //f.setMapperLocations(context.getResources("classpath:META-INF/mybatis/mapper/**/*.xml"));
        return f.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public AtomicLong nextReminderId() throws SQLException {
        try (Connection con = dataSource().getConnection()) {
            ResultSet result = con.createStatement().executeQuery("select nextval('t_user_reminder_reminder_id_seq'::regclass)");
            result.next();
            return new AtomicLong(result.getLong(1));
        }
    }
}
