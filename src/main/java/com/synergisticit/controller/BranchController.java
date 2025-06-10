package com.synergisticit.controller;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/12/2024
 */
@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private  BranchService branchService;

    @GetMapping
    public ModelAndView listBranches(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
        return new ModelAndView("branch-list") ;
    }

    @GetMapping("/new")
    private ModelAndView showCreateForm(Model model) {
        Long nextBranchId = branchService.getNextBranchId();
        model.addAttribute("branch", new Branch());
        model.addAttribute("nextBranchId", nextBranchId);
        return new ModelAndView("branch-form") ;
    }

    @PostMapping
    private ModelAndView createBranch(@ModelAttribute("branch") Branch branch) {
        branchService.createBranch(branch);
        return new ModelAndView("redirect:/branches") ;
    }

    @GetMapping("/{id}/edit")
    private ModelAndView showEditForm(@PathVariable Long id, Model model) {
        Branch branch = branchService.getBranchById(id);
        model.addAttribute("branch", branch);
        return new ModelAndView("branch-form") ;
    }

    @PostMapping("/{id}")
    private ModelAndView updateBranch(@ModelAttribute Branch branch, @PathVariable Long id) {
        branchService.updateBranch(id, branch);
        return new ModelAndView("redirect:/branches") ;
    }

    @GetMapping("/{id}/delete")
    private ModelAndView deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return new ModelAndView("redirect:/branches") ;
    }

}
