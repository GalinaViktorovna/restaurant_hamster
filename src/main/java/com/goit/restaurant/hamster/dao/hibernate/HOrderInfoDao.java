package com.goit.restaurant.hamster.dao.hibernate;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.OrderInfo;
import com.goit.restaurant.hamster.model.OrderPosition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HOrderInfoDao implements DAO<OrderInfo> {

    private SessionFactory sessionFactory;

    @Override
    public void save(OrderInfo value) {
        for (OrderPosition position: value.getOrderContent()) {
            position.setOrderInfo(value);
        }
        sessionFactory.getCurrentSession().saveOrUpdate(value);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        OrderInfo order = session.load(OrderInfo.class, id);
        session.delete(order);
    }

    @Override
    public OrderInfo getById(Long id) {
        return sessionFactory.getCurrentSession().get(OrderInfo.class, id);
    }


    @Override
    public List<OrderInfo> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select order from OrderInfo order where name like :name or table_number like :name or waiter.name like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Override
    public List<OrderInfo> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select orders from OrderInfo orders order by id desc").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}


