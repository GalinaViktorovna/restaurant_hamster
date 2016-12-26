package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MenuService {
    private DAO<Menu> menuDao;

    @Transactional
    public void save(Menu menu){
        menuDao.save(menu);
    }

    @Transactional
    public void delete(long id){
        menuDao.delete(id);
    }

    @Transactional
    public List<Menu> getByName(String name){
        return menuDao.getByName(name);
    }

    @Transactional
    public Menu getById(long id){
        return menuDao.getById(id);
    }

    @Transactional
    public List<Menu> getAll(){
        return menuDao.getAll();
    }

    public void setMenuDao(DAO<Menu> menuDao) {
        this.menuDao = menuDao;
    }

}
