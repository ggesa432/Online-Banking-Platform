package com.synergisticit.service;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 1/2/2025
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existing=getAccountById(id);
        existing.setAccountType(updatedAccount.getAccountType());
        existing.setAccountHolder(updatedAccount.getAccountHolder());
        existing.setAccountBalance(updatedAccount.getAccountBalance());
        existing.setAccountDateOpened(updatedAccount.getAccountDateOpened());
        existing.setAccountCustomer(updatedAccount.getAccountCustomer());
        existing.setAccountBranch(updatedAccount.getAccountBranch());
        return accountRepository.save(existing);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Long getNextAccountId() {
        Account maxIdAccount = accountRepository.findTopByOrderByAccountIdDesc();
        if (maxIdAccount == null) {
            return 1L;
        }
        return maxIdAccount.getAccountId()+1;
    }

    @Override
    public List<Account> getAccountsForLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        boolean isAdminOrManager = authorities.stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER")
        );

        if (isAdminOrManager) {
            return accountRepository.findAll();
        } else {

            return accountRepository.findByAccountCustomer_customerName(username);
        }
    }

    @Override
    public boolean accountExists(Long id) {
        return accountRepository.existsById(id);
    }

}
