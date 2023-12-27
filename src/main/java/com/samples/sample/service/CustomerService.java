package com.samples.sample.service;

import com.samples.sample.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    void createCustomer();

    Optional<Customer> findById(Long id);
}
