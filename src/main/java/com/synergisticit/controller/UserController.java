package com.synergisticit.controller;

import com.synergisticit.component.UserValidator;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  RoleService roleService;


    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getUsersForLoggedInUser();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {

        Long nextUserId = userService.getNextUserId();
        User user = new User();
        model.addAttribute("nextUserId", nextUserId);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user-form";
    }
    @PostMapping
    public String createUser(@ModelAttribute("user") @Validated User user,
                             BindingResult bindingResult,
                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds,
                             Model model) {

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            bindingResult.rejectValue("password", "password.empty", "Password is required");
        } else if (user.getPassword().length() < 6) {
            bindingResult.rejectValue("password", "password.tooShort", "Password must be at least 6 characters long");
        }

        if (roleIds == null || roleIds.isEmpty()) {
            bindingResult.rejectValue("roles", "role.required", "At least one role must be selected");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("nextUserId", userService.getNextUserId());
            model.addAttribute("allRoles", roleService.getAllRoles());
            model.addAttribute("selectedRoleIds", roleIds != null ? roleIds : Collections.emptyList());
            return "user-form";
        }

        if (roleIds != null) {
            List<Role> selectedRoles = roleService.getAllRoles().stream()
                    .filter(r -> roleIds.contains(r.getRoleId()))
                    .collect(Collectors.toList());
            user.setRoles(selectedRoles);
        }else {
            user.setRoles(new ArrayList<>());
        }

        userService.createUser(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User existingUser = userService.getUserById(id);
        if (existingUser==null){
            throw new IllegalArgumentException("Invalid user ID: " + id);
        }
        existingUser.setPassword("");
        model.addAttribute("user", existingUser);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user-form";
    }
    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds,
                             @ModelAttribute("user") @Validated User user,
                             BindingResult bindingResult,
                             Model model) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("Invalid user ID: " + id);
        }

        if (roleIds != null) {
            List<Role> selectedRoles = roleService.getAllRoles().stream()
                    .filter(r -> roleIds.contains(r.getRoleId()))
                    .collect(Collectors.toList());
            user.setRoles(selectedRoles);
        } else {
            user.setRoles(new ArrayList<>());
        }

        if (!existingUser.getUsername().equals(user.getUsername())) {
            if (userService.usernameExists(user.getUsername(), id)) {
                bindingResult.rejectValue("username", "username.duplicate", "Username already exists");
            }
        }

        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            if (user.getPassword().length() < 6) {
                bindingResult.rejectValue("password", "password.tooShort", "Password must be at least 6 characters long");
            }
        } else {
            user.setPassword(""); // Ensure empty password
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", "email.required", "Email is required");
        } else if (!user.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            bindingResult.rejectValue("email", "email.invalid", "Invalid email format");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleService.getAllRoles());
            return "user-form";
        }

        userService.updateUser(id, user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";

    }




}