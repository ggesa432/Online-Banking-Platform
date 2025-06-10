package com.synergisticit.service;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.User;
import com.synergisticit.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/12/2024
 */
@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Override
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElseThrow(()->new RuntimeException("Branch not found with id"+id));
    }

    @Override
    public Branch updateBranch(Long id, Branch updatedBranch) {
        Branch existing=getBranchById(id);
        existing.setBranchName(updatedBranch.getBranchName());
        existing.setBranchAddress(updatedBranch.getBranchAddress());
        return branchRepository.save(existing);
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch=getBranchById(id);
        branchRepository.delete(branch);

    }

    @Override
    public Long getNextBranchId() {
        Branch maxIdBranch = branchRepository.findTopByOrderByBranchIdDesc();
        if (maxIdBranch == null) {
            return 1L;
        }
        return maxIdBranch.getBranchId() + 1;
    }


}
