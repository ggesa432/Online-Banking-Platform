package com.synergisticit.service;

import com.synergisticit.domain.Branch;

import java.util.List;

/**
 * @author GesangZeren
 * @project  OnlineBanking
 * @date  12/12/2024
 */public interface BranchService {
     Branch createBranch(Branch branch);
     List<Branch> getAllBranches();
     Branch getBranchById(Long id);
     Branch updateBranch(Long id ,Branch branch);
     void deleteBranch(Long id);

     Long getNextBranchId();
}
