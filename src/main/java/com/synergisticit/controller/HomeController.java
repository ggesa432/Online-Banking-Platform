package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/21/2025
 */

@RestController
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home") ;
    }

    @GetMapping("/home")
    public ModelAndView homeAlt() {
        return new ModelAndView("home") ;
    }
}

