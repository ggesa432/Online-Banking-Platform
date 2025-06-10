package com.synergisticit.repository;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Component
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findTopByOrderByCustomerIdDesc();

    List<Customer> findByUser_Username(String username);
}
