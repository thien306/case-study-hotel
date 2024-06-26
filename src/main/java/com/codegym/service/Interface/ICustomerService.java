package com.codegym.service.Interface;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    Customer getCustomerByEmail(String email);

    Customer getCustomerByUsername(String username);

}
