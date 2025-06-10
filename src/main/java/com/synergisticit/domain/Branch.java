package com.synergisticit.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/10/2024
 */
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    private String branchName;

    @Embedded
    private Address branchAddress;

    @OneToMany(mappedBy = "accountBranch")
    private List<Account> branchAccount = new ArrayList<>();

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Address getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(Address branchAddress) {
        this.branchAddress = branchAddress;
    }

    public List<Account> getBranchAccount() {
        return branchAccount;
    }

    public void setBranchAccount(List<Account> branchAccount) {
        this.branchAccount = branchAccount;
    }
}
