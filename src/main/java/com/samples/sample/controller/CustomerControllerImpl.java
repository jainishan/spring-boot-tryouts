package com.samples.sample.controller;

import com.samples.sample.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerControllerImpl {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public String getAll(@PathVariable final Long id) {
        return customerService.findById(id).get().toString();
    }
}
