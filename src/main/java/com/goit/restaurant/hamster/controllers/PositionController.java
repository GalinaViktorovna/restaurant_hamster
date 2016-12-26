package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.Position;
import com.goit.restaurant.hamster.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PositionController {
    @Autowired
    private PositionService positionService;

    @RequestMapping("/positions")
    public String printList(ModelMap model) {
        model.addAttribute("positions", positionService.getAll());
        return "positions";
    }

    @RequestMapping(value = "/positions/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        positionService.delete(id);
        return "redirect:/positions";
    }

    @RequestMapping(value = "/positions/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("position", positionService.getById(id));
        return "position";
    }

    @RequestMapping(value = "/position", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("position", new Position());
        return "position";
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public String submit(@ModelAttribute("position") Position position) {
        positionService.save(position);
        return "redirect:positions";
    }
}
