package com.synergisticit.service;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.BankTransactionType;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.repository.BankTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/23/2025
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private BankTransactionRepository bankTransactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    @Override
    public Double depositAndReturnBalance(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double newBalance = account.getAccountBalance() + amount;
        enforceOwnershipOrAdmin(account);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);

        BankTransaction tx = new BankTransaction();
        tx.setBankTractionFromAccount(accountId);
        tx.setBankTractionToAccount(accountId);
        tx.setBankTransactionType(BankTransactionType.DEPOSIT);
        tx.setBankTransactionDateTime(LocalDateTime.now());
        tx.setAmount(amount);
        tx.setComments("Deposit");
        bankTransactionRepository.save(tx);

        return newBalance;
    }



    @Transactional
    @Override
    public Double withdrawAndReturnBalance(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found for ID: " + accountId));

        enforceOwnershipOrAdmin(account);

        double newBalance = account.getAccountBalance() - amount;
        account.setAccountBalance(newBalance);
        accountRepository.save(account);


        BankTransaction tx = new BankTransaction();
        tx.setBankTractionFromAccount(accountId);
        tx.setBankTractionToAccount(accountId);
        tx.setBankTransactionType(BankTransactionType.WITHDRAW);
        tx.setBankTransactionDateTime(LocalDateTime.now());
        tx.setAmount(amount);
        tx.setComments("Withdrawal of " + amount);
        bankTransactionRepository.save(tx);

        return newBalance;
    }


    @Override
    @Transactional
    public Double transferAndReturnFromBalance(Long fromAccountId, Long toAccountId, Double amount) {

        Account fromAcc = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found for ID: " + fromAccountId));
        Account toAcc = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found for ID: " + toAccountId));

        enforceOwnershipOrAdmin(fromAcc);
        fromAcc.setAccountBalance(fromAcc.getAccountBalance() - amount);
        toAcc.setAccountBalance(toAcc.getAccountBalance() + amount);

        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);

        BankTransaction tx = new BankTransaction();
        tx.setBankTractionFromAccount(fromAccountId);
        tx.setBankTractionToAccount(toAccountId);
        tx.setBankTransactionType(BankTransactionType.TRANSFER);
        tx.setBankTransactionDateTime(LocalDateTime.now());
        tx.setAmount(amount);
        tx.setComments("Transfer from account " + fromAccountId + " to " + toAccountId);
        bankTransactionRepository.save(tx);

        return fromAcc.getAccountBalance();
    }

    private void enforceOwnershipOrAdmin(Account account) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdminOrManager = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                        || a.getAuthority().equals("ROLE_MANAGER"));

        if (!isAdminOrManager) {
            // normal user => must match accountHolder
            String username = auth.getName(); // logged-in user
            if (!account.getAccountCustomer().getCustomerName().equals(username)) {
                throw new RuntimeException("Access denied: it is not your account");
            }
        }
    }


}
