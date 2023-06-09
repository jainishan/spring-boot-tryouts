package com.samples.sample;

import com.samples.sample.config.BaseIntegrationTestJDBC;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

class RepositoryTestWithJDBC extends BaseIntegrationTestJDBC {

    @Test
    @Sql("classpath:sql/acquire-bill-gates.sql")
    void testJDBC() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList("SELECT * FROM customer");
        System.out.println(data);
    }

}
