package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/27/2025
 */

@RestController
public class AuthController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login") ; // Return the JSP named "login.jsp"
    }
}

