package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.OrderInfo;
import com.goit.restaurant.hamster.service.DishService;
import com.goit.restaurant.hamster.service.EmployeeService;
import com.goit.restaurant.hamster.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
@Controller
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DishService dishService;

    @RequestMapping("/orders")
    public String printList(ModelMap model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders";
    }

    @RequestMapping("/orders/open")
    public String printOpen(ModelMap model) {
        model.addAttribute("orders", orderService.getOpen());
        return "orders";
    }

    @RequestMapping(value = "/orders/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        orderService.delete(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("dishes", dishService.getAll());
        model.addAttribute("order", orderService.getById(id));
        return "order";
    }

    @RequestMapping(value = "/orders/view/{id}", method=RequestMethod.GET)
    public String view(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("order", orderService.getById(id));
        return "order-preview";
    }

    @RequestMapping(value = "/orders/search", method=RequestMethod.GET)
    public String find(@RequestParam("name")String name, ModelMap model) {
        model.addAttribute("orders", orderService.getByName(name));
        return "orders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("dishes", dishService.getAll());
        model.addAttribute("order", new OrderInfo());
        return "order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String submit(@ModelAttribute("orderInfo") OrderInfo order) {
        orderService.save(order);
        return "redirect:orders";
    }

    public void setOrderService(OrderInfoService orderService) {
        this.orderService = orderService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
