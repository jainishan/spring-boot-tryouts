package com.samples.sample;

import com.samples.sample.config.BaseIntegrationTestJDBCExtension;
import com.samples.sample.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

class SpyBeanTest extends BaseIntegrationTestJDBCExtension {

    @SpyBean
    private CustomerService customerService;

    @Test
    @Sql("classpath:sql/acquire-bill-gates.sql")
    void testSpyBean() {
        verifyNoInteractions(customerService);
    }

}
