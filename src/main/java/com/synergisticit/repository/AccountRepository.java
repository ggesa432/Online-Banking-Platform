package com.synergisticit.repository;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findTopByOrderByAccountIdDesc();

    List<Account> findByAccountCustomer_customerName(String username);



}
