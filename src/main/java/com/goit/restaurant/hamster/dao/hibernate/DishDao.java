package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.DishComponent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DishDao implements DAO<Dish> {

    private SessionFactory sessionFactory;

    @Override
    public void save(Dish dish) {
        for (DishComponent position : dish.getComponents()) {
            position.setDish(dish);
        }
        sessionFactory.getCurrentSession().saveOrUpdate(dish);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = session.load(Dish.class, id);
        session.delete(dish);
    }

    @Override
    public Dish getById(Long id) {
        return sessionFactory.getCurrentSession().get(Dish.class, id);
    }

    @Override
    public List getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select dish from Dish dish where name like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Override
    public List<Dish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select dish from Dish dish").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}


