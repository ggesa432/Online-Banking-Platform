package com.synergisticit.controller;

import com.synergisticit.component.AccountValidator;
import com.synergisticit.domain.Account;
import com.synergisticit.domain.User;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/16/2025
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BranchService branchService;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @GetMapping
    public ModelAndView listAccounts(Model model) {
        List<Account> allAccounts = accountService.getAccountsForLoggedInUser();
        model.addAttribute("accounts", allAccounts);
        return new ModelAndView("account-list") ;
    }
    @GetMapping("/new")
    public ModelAndView showCreateForm(Model model) {
        Long nextAccountId = accountService.getNextAccountId();
        Account account = new Account();
        account.setAccountDateOpened(LocalDate.now());
        model.addAttribute("account", account);
        model.addAttribute("nextAccountId", nextAccountId);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("branches", branchService.getAllBranches());
        return new ModelAndView("account-form") ;
    }
    @PostMapping
    public ModelAndView createAccount(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nextAccountId", accountService.getNextAccountId());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("branches", branchService.getAllBranches());
            return new ModelAndView("account-form") ;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        account.setAccountHolder(username);
        accountService.createAccount(account);
        return new ModelAndView("redirect:/accounts") ;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id, Model model) {
        Account existingAccount = accountService.getAccountById(id);
        model.addAttribute("account", existingAccount);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("branches", branchService.getAllBranches());
        return new ModelAndView("account-form") ;
    }
    @PostMapping("/{id}")
    public ModelAndView updateAccount(@PathVariable Long id, @ModelAttribute("account") @Valid Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("branches", branchService.getAllBranches());
            return new ModelAndView("account-form") ;
        }

        accountService.updateAccount(id, account);
        return new ModelAndView("redirect:/accounts") ;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ModelAndView("redirect:/accounts") ;
    }

}