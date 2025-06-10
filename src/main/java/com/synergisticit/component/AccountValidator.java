package com.synergisticit.component;

import com.synergisticit.domain.Account;
import org.eclipse.tags.shaded.org.apache.bcel.generic.IF_ACMPEQ;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * @author GesangZeren
 * @project OnlineBank - Assessment
 * @date 1/29/2025
 */
@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;

        if (account.getAccountType()==null) {
            errors.rejectValue("accountType", "account.type.required", "Account Type is required");
        }

        if (account.getAccountDateOpened()==null){
            errors.rejectValue("accountDateOpened", "account.date.opened.required", "Date Opened is required");
        }else if (account.getAccountDateOpened().isAfter(LocalDate.now())){
            errors.rejectValue("accountDateOpened", "account.date.opened.future", "Date Opened cannot be in the future");
        }

        if (account.getAccountBalance()<0){
            errors.rejectValue("accountBalance", "account.balance.negative", "Account Balance cannot be negative");
        }

        if (account.getAccountCustomer()==null){
            errors.rejectValue("accountCustomer", "account.customer.required", "Customer is required");
        }

        if (account.getAccountBranch()==null){
            errors.rejectValue("accountBranch", "account.branch.required", "Branch is required");
        }


    }
}
