package com.synergisticit.repository;

import com.synergisticit.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
}
