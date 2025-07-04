package com.synergisticit.dto;



/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/23/2025
 */
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;


    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
