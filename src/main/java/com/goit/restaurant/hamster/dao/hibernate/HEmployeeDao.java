package com.goit.restaurant.hamster.dao.hibernate;


import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HEmployeeDao implements DAO<Employee> {
    private SessionFactory sessionFactory;

    @Override
    public void save(Employee value) {
        sessionFactory.getCurrentSession().saveOrUpdate(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.load(Employee.class, id);
        session.delete(employee);
    }

    @Override
    public Employee getById(Long id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    public List<Employee> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select employee from Employee employee where name like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select employee from Employee employee").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
