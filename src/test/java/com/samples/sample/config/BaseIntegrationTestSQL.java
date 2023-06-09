package com.samples.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

@ActiveProfiles("it")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/sql/cleanup.sql")
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
public abstract class BaseIntegrationTestSQL {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
