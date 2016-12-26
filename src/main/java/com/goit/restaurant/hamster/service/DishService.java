package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DishService {
    private DAO<Dish> dishDao;

    @Transactional
    public void save(Dish dish) {
        dishDao.save(dish);
    }

    @Transactional
    public void delete(long id) {
        dishDao.delete(id);
    }

    @Transactional
    public List<Dish> getByName(String name) {
        return dishDao.getByName(name);
    }

    @Transactional
    public Dish getById(long id) {
        return dishDao.getById(id);
    }

    @Transactional
    public List<Dish> getAll() {
        return dishDao.getAll();
    }

    public void setDishDao(DAO<Dish> dishDao) {
        this.dishDao = dishDao;
    }
}
