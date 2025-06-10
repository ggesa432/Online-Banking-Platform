package com.synergisticit.service;

import com.synergisticit.domain.Customer;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id,Customer updatedCustomer);
    void deleteCustomer(Long id);
    Long getNextCustomerId();

    List<Customer> getAllCustomersForLoggedInUser();
}
