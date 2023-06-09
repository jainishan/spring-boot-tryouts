package com.samples.sample;

import com.samples.sample.config.BaseIntegrationTestSQL;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;

class RepositoryTestWithSQL extends BaseIntegrationTestSQL {


    @Test
	@Sql("classpath:sql/acquire-bill-gates.sql")
    void testSQL() {
		Map<String, Object> data = jdbcTemplate.queryForMap("SELECT * FROM customer");
		System.out.println(data);
    }

}
