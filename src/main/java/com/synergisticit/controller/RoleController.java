package com.synergisticit.controller;

import com.synergisticit.component.RoleValidator;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private  RoleService roleService;
    @Autowired
    private RoleValidator roleValidator;


    @GetMapping
    public ModelAndView listRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return new ModelAndView("role-list") ;
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm(Model model) {
        Long nextRoleId = roleService.getNextRoleId();
        Role role = new Role();
        model.addAttribute("nextRoleId", nextRoleId);
        model.addAttribute("role", role);
        return new ModelAndView ("role-form");
    }

    @PostMapping
    public ModelAndView createRole(@ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {

        roleValidator.validate(role, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("nextRoleId", roleService.getNextRoleId());
            model.addAttribute("errors", bindingResult);
            return new ModelAndView("role-form");
        }
        roleService.createRole(role);
        return new ModelAndView("redirect:/roles") ;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return new ModelAndView("role-form") ;
    }

    @PostMapping("/{id}")
    public ModelAndView updateRole(@PathVariable Long id, @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
        role.setRoleId(id);
        roleValidator.validate(role, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return new ModelAndView("role-form") ;
        }
        roleService.updateRole(id, role);
        return new ModelAndView("redirect:/roles") ;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ModelAndView("redirect:/roles") ;
    }

    @GetMapping("/roles")
    public ModelAndView getRolesSorted(Model model) {
        List<Role> sortedRoles = roleService.getAllRolesSorted();
        model.addAttribute("roles", sortedRoles);
        return new ModelAndView("role-list") ;
    }
}

