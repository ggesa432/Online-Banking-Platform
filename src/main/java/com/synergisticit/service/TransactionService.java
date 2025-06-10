package com.synergisticit.service;

import jakarta.transaction.Transactional;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/23/2025
 */
public interface TransactionService {

    Double depositAndReturnBalance(Long accountId, Double amount);

    Double withdrawAndReturnBalance(Long accountId, Double amount);

    Double transferAndReturnFromBalance(Long fromAccountId, Long toAccountId, Double transferAmount);
}
