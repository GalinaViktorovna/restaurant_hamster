package com.goit.restaurant.hamster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPageController {
    @RequestMapping
    public String printMain(ModelMap model) {
        model.addAttribute("admin", true);
        return "main";
    }

    @RequestMapping(value = "tables")
    public String printTables(ModelMap model) {
        return "tables";
    }

    @RequestMapping(value = "contacts")
    public String printContacts(ModelMap model) {
        return "contacts";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}
