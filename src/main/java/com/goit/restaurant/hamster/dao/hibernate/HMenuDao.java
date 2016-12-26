package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HMenuDao implements DAO<Menu> {
    private SessionFactory sessionFactory;

    @Override
    public void save(Menu value) {
        sessionFactory.getCurrentSession().saveOrUpdate(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Menu menu = session.load(Menu.class, id);
        session.delete(menu);
    }

    @Override
    public Menu getById(Long id) {
        return sessionFactory.getCurrentSession().get(Menu.class, id);
    }

    @Override
    public List<Menu> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select menu from Menu menu where name like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Override
    public List<Menu> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select menu from Menu menu").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
