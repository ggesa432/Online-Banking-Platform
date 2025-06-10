package com.synergisticit.repository;

import com.synergisticit.domain.Branch;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findTopByOrderByBranchIdDesc();
}
