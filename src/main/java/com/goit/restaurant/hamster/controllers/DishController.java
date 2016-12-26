package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.service.DishService;
import com.goit.restaurant.hamster.service.ImageService;
import com.goit.restaurant.hamster.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/dishes")
    public String printList(ModelMap model) {
        model.addAttribute("dishes", dishService.getAll());
        return "dishes";
    }

    @RequestMapping(value = "/dishes/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @RequestMapping(value = "/dishes/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        model.addAttribute("dish", dishService.getById(id));
        return "dish";
    }

    @RequestMapping(value = "/dishes/search", method=RequestMethod.GET)
    public String find(@RequestParam("name")String name, ModelMap model) {
        model.addAttribute("dishes", dishService.getByName(name));
        return "dishes";
    }


    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        model.addAttribute("dish", new Dish());
        return "dish";
    }

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public String submit(@ModelAttribute("dish") Dish dish, @RequestParam CommonsMultipartFile[] img) {
        if (img != null && img.length > 0) {
            for (CommonsMultipartFile aFile : img){
                dish.setPhoto(imageService.createImage(aFile.getBytes()));
            }
        }
        dishService.save(dish);
        return "redirect:dishes";
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder){
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }}
