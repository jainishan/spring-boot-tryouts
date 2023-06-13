package com.samples.sample;

import com.samples.sample.config.BaseIntegrationTestJDBCExtension;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTestWithJDBCExtension extends BaseIntegrationTestJDBCExtension {

    @Test
    @Sql("classpath:sql/acquire-bill-gates.sql")
    void testJDBC() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList("SELECT * FROM customer");
        System.out.println(data);
        assertEquals(1, data.size());
    }

}
