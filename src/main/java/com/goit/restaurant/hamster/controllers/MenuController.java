package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.Menu;
import com.goit.restaurant.hamster.service.DishService;
import com.goit.restaurant.hamster.service.ImageService;
import com.goit.restaurant.hamster.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/menus")
    public String printList(ModelMap model) {
        model.addAttribute("menus", menuService.getAll());
        return "menus";
    }

    @RequestMapping(value = "/menus/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        menuService.delete(id);
        return "redirect:/menus";
    }

    @RequestMapping(value = "/menus/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("allDishes", dishService.getAll());
        model.addAttribute("menu", menuService.getById(id));
        return "menu";
    }

    @RequestMapping(value = "/menus/search", method=RequestMethod.GET)
    public String find(@RequestParam("name")String name, ModelMap model) {
        model.addAttribute("menus", menuService.getByName(name));
        return "menus";
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("allDishes", dishService.getAll());
        model.addAttribute("menu", new Menu());
        return "menu";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public String submit(@ModelAttribute("menu") Menu menu, @RequestParam CommonsMultipartFile[] img ) {
        if (img != null && img.length > 0) {
            for (CommonsMultipartFile aFile : img){
                menu.setPhoto(imageService.createImage(aFile.getBytes()));
            }
        }
        menuService.save(menu);
        return "redirect:menus";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

}
