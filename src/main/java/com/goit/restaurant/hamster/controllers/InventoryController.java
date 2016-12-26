package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.Ingredient;
import com.goit.restaurant.hamster.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class InventoryController {
    @Autowired
    private IngredientService ingredientService;

    @RequestMapping("/inventory")
    public String printList(ModelMap model) {
        model.addAttribute("inventory", ingredientService.getAll());
        return "inventory";
    }

    @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id, ModelMap model) {
        ingredientService.delete(id);
        return "redirect:/inventory";
    }

    @RequestMapping(value = "/inventory/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("inventoryItem", ingredientService.getById(id));
        return "inventoryItem";
    }

    @RequestMapping(value = "/inventory/search", method = RequestMethod.GET)
    public String find(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("inventory", ingredientService.getByName(name));
        return "inventory";
    }

    @RequestMapping(value = "/inventory/ending", method = RequestMethod.GET)
    public String findEnding(ModelMap model) {
        model.addAttribute("inventory", ingredientService.getEnding());
        return "inventory";
    }

    @RequestMapping(value = "/inventoryItem", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("inventoryItem", new Ingredient());
        return "inventoryItem";
    }

    @RequestMapping(value = "/inventoryItem", method = RequestMethod.POST)
    public String submit(@ModelAttribute("inventoryItem") Ingredient ingredient) {
        ingredientService.save(ingredient);
        return "redirect:inventory";
    }
}
