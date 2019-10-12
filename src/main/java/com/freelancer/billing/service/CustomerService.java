package com.freelancer.billing.service;

import com.freelancer.billing.domain.Customer;
import com.freelancer.billing.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> findCustomerByNameOrSocialId(String value);

    Customer findCustomerById(String customerId);

    void saveCustomer(CustomerDTO customer);

    List<Customer> findAll();

    void removeCustomer(String customerId);

    CustomerDTO findCustomerDetailsById(String customerId);

    void updateCustomer(CustomerDTO customer, String id);
}
