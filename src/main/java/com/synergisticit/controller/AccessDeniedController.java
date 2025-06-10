package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author GesangZeren
 * @project OnlineBank - Assessment
 * @date 1/28/2025
 */
@RestController
public class AccessDeniedController {
    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("accessDenied") ;
    }
}
