package com.synergisticit.domain;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountHolder;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate accountDateOpened;

    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer accountCustomer;

    @ManyToOne
    @JoinColumn(name="branchId")
    private Branch accountBranch;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public LocalDate getAccountDateOpened() {
        return accountDateOpened;
    }

    public void setAccountDateOpened(LocalDate accountDateOpened) {
        this.accountDateOpened = accountDateOpened;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Customer getAccountCustomer() {
        return accountCustomer;
    }

    public void setAccountCustomer(Customer accountCustomer) {
        this.accountCustomer = accountCustomer;
    }

    public Branch getAccountBranch() {
        return accountBranch;
    }

    public void setAccountBranch(Branch accountBranch) {
        this.accountBranch = accountBranch;
    }
}