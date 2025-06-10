package com.synergisticit.service;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;
import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById((long) Math.toIntExact(id)).orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        // 1) find existing
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2) Check role + ownership
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdminOrManager = auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER"));

        // If normal user, check that existing.getUser().getUsername() == username
        if (!isAdminOrManager) {
            if (!existing.getUser().getUsername().equals(username)) {
                throw new RuntimeException("Access denied: not your customer");
            }
        }
        if (isAdminOrManager) {
            existing.setCustomerName(updatedCustomer.getCustomerName());
        }
        existing.setCustomerAddress(updatedCustomer.getCustomerAddress());
        existing.setCustomerDOB(updatedCustomer.getCustomerDOB());
        existing.setCustomerSSN(updatedCustomer.getCustomerSSN());
        existing.setCustomerGender(updatedCustomer.getCustomerGender());

        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById((long) Math.toIntExact(id));
    }

    @Override
    public Long getNextCustomerId() {
        Customer maxIdCustomer = customerRepository.findTopByOrderByCustomerIdDesc();
        if (maxIdCustomer == null) {
            return 1L;
        }
        return maxIdCustomer.getCustomerId() + 1;
    }

    @Override
    public List<Customer> getAllCustomersForLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdminOrManager = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") ||
                        a.getAuthority().equals("ROLE_MANAGER"));

        if (isAdminOrManager) {
            return customerRepository.findAll();
        } else {
            return customerRepository.findByUser_Username(username);
        }
    }


}
