package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HPositionDao implements DAO<Position> {
    private SessionFactory sessionFactory;

    @Override
    public void save(Position value) {
        sessionFactory.getCurrentSession().save(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Position position = session.load(Position.class, id);
        session.delete(position);
    }

    @Override
    public Position getById(Long id) {
        return sessionFactory.getCurrentSession().get(Position.class, id);

    }

    @Override
    public List<Position> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select position from Position position where name like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Position> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select position from Position position").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
