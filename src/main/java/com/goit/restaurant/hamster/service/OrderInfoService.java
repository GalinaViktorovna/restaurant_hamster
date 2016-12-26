package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.OrderInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderInfoService {
    private DAO<OrderInfo> orderDao;

    @Transactional
    public void save(OrderInfo order){
        orderDao.save(order);
    }

    @Transactional
    public void delete(long id){
        orderDao.delete(id);
    }

    @Transactional
    public List<OrderInfo> getByName(String name){
        return orderDao.getByName(name);
    }

    @Transactional
    public OrderInfo getById(long id){
        return orderDao.getById(id);
    }

    @Transactional
    public List<OrderInfo> getAll(){
        return orderDao.getAll();
    }

    @Transactional
    public List<OrderInfo> getOpen(){
        return null;
    }

    public void setOrderDao(DAO<OrderInfo> orderDao) {
        this.orderDao = orderDao;
    }

}
