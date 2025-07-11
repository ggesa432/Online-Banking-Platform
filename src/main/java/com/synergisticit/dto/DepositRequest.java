package com.synergisticit.dto;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/23/2025
 */
public class DepositRequest {
    private Long accountId;
    private Double amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
