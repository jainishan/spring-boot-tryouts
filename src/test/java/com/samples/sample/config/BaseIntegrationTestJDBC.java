package com.samples.sample.config;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("it")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTestJDBC {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        cleanDatabase();
    }

    private void cleanDatabase() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

        List<String> tableNames = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE()", String.class);
        for (String tableName : tableNames) {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
        }

        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }
}
