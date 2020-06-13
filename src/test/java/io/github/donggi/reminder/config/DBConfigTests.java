package io.github.donggi.reminder.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class DBConfigTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void dataSourceSet() {
        try (Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement();)
        {
            stmt.executeUpdate("create table if not exists test (id bigserial primary key, name text, add_date timestamptz default now())");
            stmt.executeUpdate("insert into test (name) values ('dgkim1'), ('dgkim2')");
            
            ResultSet rs = stmt.executeQuery("select name from test");
            while (rs.next()) {
                log.info("Read from DB: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
