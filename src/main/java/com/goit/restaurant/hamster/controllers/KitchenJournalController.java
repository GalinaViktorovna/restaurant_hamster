package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.KitchenJournal;
import com.goit.restaurant.hamster.service.DishService;
import com.goit.restaurant.hamster.service.EmployeeService;
import com.goit.restaurant.hamster.service.KitchenJournalService;
import com.goit.restaurant.hamster.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KitchenJournalController {
    @Autowired
    private KitchenJournalService kitchenJournalService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping("/kitchen")
    public String printList(ModelMap model) {
        model.addAttribute("kitchenJournal", kitchenJournalService.getAll());
        return "kitchen";
    }

    @RequestMapping(value = "/kitchen/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        kitchenJournalService.delete(id);
        return "redirect:/kitchen";
    }

    @RequestMapping(value = "/kitchen/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("dishes", dishService.getAll());
        model.addAttribute("orders", orderInfoService.getAll());
        model.addAttribute("kitchenItem", kitchenJournalService.getById(id));
        return "kitchenItem";
    }

    @RequestMapping(value = "/kitchenItem", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("dishes", dishService.getAll());
        model.addAttribute("orders", orderInfoService.getAll());
        model.addAttribute("kitchenItem", new KitchenJournal());
        return "kitchenItem";
    }

    @RequestMapping(value = "/kitchenItem", method = RequestMethod.POST)
    public String submit(@ModelAttribute("kitchenItem") KitchenJournal kitchenJournal) {
        kitchenJournalService.save(kitchenJournal);
        return "redirect:kitchen";
    }

    public void setKitchenJournalService(KitchenJournalService kitchenJournalService) {
        this.kitchenJournalService = kitchenJournalService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public void setOrderService(OrderInfoService orderService) {
        this.orderInfoService = orderService;
    }

}
