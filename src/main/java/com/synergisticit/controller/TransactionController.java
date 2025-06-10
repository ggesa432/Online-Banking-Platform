package com.synergisticit.controller;

import com.synergisticit.component.DepositRequestValidator;
import com.synergisticit.component.TransferRequestValidator;
import com.synergisticit.component.WithdrawRequestValidator;
import com.synergisticit.dto.DepositRequest;
import com.synergisticit.dto.TransferRequest;
import com.synergisticit.dto.WithdrawRequest;
import com.synergisticit.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/23/2025
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private DepositRequestValidator depositValidator;
    @Autowired
    private WithdrawRequestValidator withdrawValidator;
    @Autowired
    private TransferRequestValidator transferValidator;

    @InitBinder("depositRequest")
    public void initDepositBinder(WebDataBinder binder) {
        binder.addValidators(depositValidator);
    }

    @InitBinder("withdrawRequest")
    public void initWithdrawBinder(WebDataBinder binder) {
        binder.addValidators(withdrawValidator);
    }

    @InitBinder("transferRequest")
    public void initTransferBinder(WebDataBinder binder) {
        binder.addValidators(transferValidator);
    }



    @GetMapping("/deposit")
    public ModelAndView showDepositForm(Model model) {
        model.addAttribute("depositRequest", new DepositRequest());
        return new ModelAndView("deposit-form") ;
    }

    @PostMapping("/deposit")
    public ModelAndView doDeposit(@ModelAttribute("depositRequest") @Valid DepositRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("deposit-form") ;
        }
        Double newBalance = transactionService.depositAndReturnBalance(request.getAccountId(), request.getAmount());
        redirectAttributes.addFlashAttribute("depositAmount", request.getAmount());
        redirectAttributes.addFlashAttribute("newBalance", newBalance);
        return new ModelAndView("redirect:/transactions/depositResult") ;
    }
    @GetMapping("/depositResult")
    public ModelAndView showDepositResult() {
        return new ModelAndView("deposit-result") ;
    }



    @GetMapping("/withdraw")
    public ModelAndView showWithdrawForm(Model model) {
        model.addAttribute("withdrawRequest", new WithdrawRequest());
        return new ModelAndView("withdraw-form");
    }

    @PostMapping("/withdraw")
    public ModelAndView doWithdraw(@ModelAttribute("withdrawRequest") @Valid WithdrawRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("withdraw-form") ;
        }
        Double withdrawnAmount = request.getAmount();
        Double newBalance = transactionService.withdrawAndReturnBalance(request.getAccountId(), withdrawnAmount);
        redirectAttributes.addFlashAttribute("withdrawnAmount", withdrawnAmount);
        redirectAttributes.addFlashAttribute("newBalance", newBalance);
        return new ModelAndView("redirect:/transactions/withdrawResult") ;
    }

    @GetMapping("/withdrawResult")
    public ModelAndView showWithdrawResult() {
        return new ModelAndView("withdraw-result") ;
    }

    // 3) Transfer
    @GetMapping("/transfer")
    public ModelAndView showTransferForm(Model model) {
        model.addAttribute("transferRequest", new TransferRequest());
        return new ModelAndView("transfer-form") ;
    }

    @PostMapping("/transfer")
    public ModelAndView doTransfer(@ModelAttribute("transferRequest") @Valid TransferRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("transfer-form") ;
        }

        Double transferAmount = request.getAmount();

        Double fromNewBalance = transactionService.transferAndReturnFromBalance(
                request.getFromAccountId(),
                request.getToAccountId(),
                transferAmount
        );

        redirectAttributes.addFlashAttribute("transferAmount", transferAmount);
        redirectAttributes.addFlashAttribute("fromAccountId", request.getFromAccountId());
        redirectAttributes.addFlashAttribute("toAccountId", request.getToAccountId());
        redirectAttributes.addFlashAttribute("fromNewBalance", fromNewBalance);

        return new ModelAndView("redirect:/transactions/transferResult") ;
    }

    @GetMapping("/transferResult")
    public ModelAndView showTransferResult() {
        return new ModelAndView("transfer-result") ;
    }
}

