package com.synergisticit.component;

import com.synergisticit.domain.Account;
import com.synergisticit.dto.TransferRequest;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author GesangZeren
 * @project OnlineBank - Assessment
 * @date 1/29/2025
 */
@Component
public class TransferRequestValidator implements Validator {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferRequest request = (TransferRequest) target;

        if (request.getFromAccountId()==null){
            errors.rejectValue("fromAccountId","fromAccount.required","From account is required");
        }else if (!accountService.accountExists(request.getFromAccountId())){
            errors.rejectValue("fromAccountId","fromAccount.notExist","From account dose not exist");
        } else {
            Account fromAccount = accountService.getAccountById(request.getFromAccountId());
            if (request.getAmount() != null && fromAccount.getAccountBalance() < request.getAmount()) {
                errors.rejectValue("amount", "transfer.insufficient",
                        "Insufficient balance to transfer $" + request.getAmount());
            }
        }

        if (request.getToAccountId()==null){
            errors.rejectValue("toAccountId","toAccount.required","To account is required");
        }else if (!accountService.accountExists(request.getToAccountId())){
            errors.rejectValue("toAccountId","toAccount.notExist","To account does not exist");
        }

        //same account check
        if (request.getFromAccountId()!=null && request.getToAccountId()!=null && request.getFromAccountId().equals(request.getToAccountId())){
            errors.rejectValue("toAccountId","account.same","Cannot transfer to the same account");
        }

        if (request.getAmount() == null ) {
            errors.rejectValue("amount", "amount.required", "Amount is required");
        }else if (request.getAmount() <= 0){
            errors.rejectValue("amount", "amount.invalid", "Amount must be greater than 0");
        }
    }
}
