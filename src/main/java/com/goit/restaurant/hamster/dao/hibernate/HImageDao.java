package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HImageDao implements DAO<Image> {
    private SessionFactory sessionFactory;

    @Override
    public void save(Image value) {
        sessionFactory.getCurrentSession().save(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Image image = session.load(Image.class, id);
        session.delete(image);
    }

    @Override
    public Image getById(Long id) {
        return sessionFactory.getCurrentSession().get(Image.class, id);

    }

    @Override
    public List<Image> getByName(String name) {
        return null;
    }

    @Override
    public List<Image> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select image from Image image").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
