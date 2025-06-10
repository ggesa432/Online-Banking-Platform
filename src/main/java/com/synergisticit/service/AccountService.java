package com.synergisticit.service;

import com.synergisticit.domain.Account;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 1/2/2025
 */
public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id );
    List<Account> getAllAccounts();
    Account updateAccount(Long id, Account updatedAccount);
    void deleteAccount(Long id);
    Long getNextAccountId();

    List<Account> getAccountsForLoggedInUser();
    boolean accountExists(Long id);
}
