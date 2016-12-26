package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.model.Employee;
import com.goit.restaurant.hamster.service.EmployeeService;
import com.goit.restaurant.hamster.service.ImageService;
import com.goit.restaurant.hamster.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/employees")
    public String printList(ModelMap model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employees";
    }

    @RequestMapping(value = "/employees/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable("id")long id, ModelMap model) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id")long id, ModelMap model) {
        model.addAttribute("positions", positionService.getAll());
        model.addAttribute("employee", employeeService.getById(id));
        return "employee";
    }

    @RequestMapping(value = "/employees/search", method=RequestMethod.GET)
    public String find(@RequestParam("name")String name, ModelMap model) {
        model.addAttribute("employees", employeeService.getByName(name));
        return "employees";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("positions", positionService.getAll());
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String submit(@ModelAttribute("employee") Employee employee, @RequestParam CommonsMultipartFile[] img) {
        if (img != null && img.length > 0) {
            for (CommonsMultipartFile aFile : img){
                employee.setPhoto(imageService.createImage(aFile.getBytes()));
            }
        }
        employeeService.save(employee);
        return "redirect:employees";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }
   }

