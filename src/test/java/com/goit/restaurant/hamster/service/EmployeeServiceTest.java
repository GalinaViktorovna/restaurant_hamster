package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    
    @Before
    public void setUp() throws Exception {
        List<Employee> all = employeeService.getAll();
        all.stream().forEach(e -> employeeService.delete(e.getId()));

        Employee employee = new Employee();
        employee.setName("test employee");
        employeeService.save(employee);

        Employee employee2 = new Employee();
        employee2.setName("test employee 2");
        employeeService.save(employee2);
    }

    @Test
    public void save() throws Exception {
        Employee employee = new Employee();
        employee.setName("test employee 3");
        employeeService.save(employee);

        List<Employee> all = employeeService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        employeeService.delete(2L);

        List<Employee> all = employeeService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getByName() throws Exception {
        List<Employee> all = employeeService.getByName("test employee");
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Employee> all = employeeService.getAll();
        Employee employee = all.get(0);
        String expectedName = employee.getName();

        employee = employeeService.getById(employee.getId());

        Assert.assertEquals(employee.getName(), expectedName);
    }

    @Test
    public void getAll() throws Exception {
        List<Employee> all = employeeService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

}