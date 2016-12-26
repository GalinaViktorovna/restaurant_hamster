package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeService {
    private DAO<Employee> employeeDao;

    @Transactional
    public void save(Employee employee){
        employeeDao.save(employee);
    }

    @Transactional
    public void delete(long id){
        employeeDao.delete(id);
    }

    @Transactional
    public List<Employee> getByName(String name){
        return employeeDao.getByName(name);
    }

    @Transactional
    public Employee getById(long id){
        return employeeDao.getById(id);
    }

    @Transactional
    public List<Employee> getAll(){
        return employeeDao.getAll();
    }

    public void setEmployeeDao(DAO<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }

}
