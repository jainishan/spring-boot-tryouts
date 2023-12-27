package com.sample.sample.config;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

public class DBCleanupExtension implements BeforeEachCallback {

    private JdbcTemplate jdbcTemplate;
    private static final String DB_CHANGELOG_TABLE_NAME = "DATABASECHANGELOG";

    @Override
    public void beforeEach(final ExtensionContext extensionContext) {
        if (jdbcTemplate == null) {
            jdbcTemplate = SpringExtension.getApplicationContext(extensionContext).getBean(JdbcTemplate.class);
        }
        cleanDatabase();
    }

    private void cleanDatabase() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

        List<String> tableNames = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE()", String.class);

        for (String tableName : tableNames) {
            if (DB_CHANGELOG_TABLE_NAME.equalsIgnoreCase(tableName)) {
                continue;
            }

            jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
        }

        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }
}
