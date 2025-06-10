package com.synergisticit.controller;


import com.synergisticit.component.CustomerValidator;
import com.synergisticit.domain.Address;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/18/2024
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerValidator customerValidator;

    @InitBinder("customer")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(customerValidator);
    }

    @GetMapping
    public ModelAndView listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomersForLoggedInUser();
        model.addAttribute("customers", customers);
        return new ModelAndView("customer-list") ;
    }
    @GetMapping("/new")
    public ModelAndView showCreateForm(Model model) {
        Customer customer=new Customer();
        Long nextCusomerId = customerService.getNextCustomerId();
        if (customer.getCustomerAddress()==null){
            customer.setCustomerAddress(new Address());
        }
        model.addAttribute("customer", customer);
        model.addAttribute("nextCustomerId", nextCusomerId);
        model.addAttribute("allUsers", userRepository.findAll());
        return new ModelAndView("customer-form") ;
    }
    @PostMapping
    public ModelAndView createCustomer(@RequestParam(name = "userId", required = false) Long userId,
                                 @ModelAttribute("customer") @Validated Customer customer,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nextCustomerId", customerService.getNextCustomerId());
            model.addAttribute("allUsers", userRepository.findAll());
            return new ModelAndView("customer-form") ;
        }

        if (userId != null) {
            User user = userRepository.findById((long) Math.toIntExact(userId))
                    .orElseThrow(() -> new RuntimeException("User not found"));
            customer.setUser(user);
        }
        customerService.createCustomer(customer);
        return new ModelAndView("redirect:/customers") ;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        if (customer.getCustomerAddress()==null){
            customer.setCustomerAddress(new Address());
        }

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("customer", customer);
        model.addAttribute("allUsers", allUsers);
        return new ModelAndView("customer-form") ;
    }
    @PostMapping("/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id,
                                 @ModelAttribute("customer") @Validated Customer customer,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allUsers", userService.getAllUsers());
            return new ModelAndView("customer-form") ;
        }
        customerService.updateCustomer(id, customer);
        return new ModelAndView("redirect:/customers") ;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ModelAndView("redirect:/customers") ;
    }
}
