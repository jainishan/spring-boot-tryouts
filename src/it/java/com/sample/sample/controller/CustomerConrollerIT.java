package com.sample.sample.controller;

import com.sample.sample.config.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerConrollerIT extends BaseIntegrationTest {

    @Test
    @SneakyThrows
    public void getPaymentLinkSuccess() {

        mvc.perform(get("/v1/customer/100")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpectAll(jsonPath("$.id").value(100),
                        jsonPath("$.type").value(""),
                        jsonPath("$.status").value(""));
    }
}
