package com.synergisticit.component;

import com.synergisticit.domain.Account;
import com.synergisticit.dto.WithdrawRequest;
import com.synergisticit.service.AccountService;
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
public class WithdrawRequestValidator implements Validator {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return WithdrawRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WithdrawRequest request = (WithdrawRequest) target;

        if (request.getAccountId()==null) {
            errors.rejectValue("accountId", "account.required","Account ID is required");
        }else if (!accountService.accountExists(request.getAccountId())) {
            errors.rejectValue("accountId", "account.notExist","Account does not found");
        }else {
            Account account=accountService.getAccountById(request.getAccountId());
            if (request.getAmount()!=null && account.getAccountBalance()< request.getAmount()) {
                errors.rejectValue("amount", "withdraw.insufficient","Insufficient balance to withdraw $" + request.getAmount());
            }
        }




        if (request.getAmount() == null ) {
            errors.rejectValue("amount", "amount.required", "Amount is required");
        }else if (request.getAmount() <= 0){
            errors.rejectValue("amount", "amount.invalid", "Amount must be greater than 0");
        }
    }
}
