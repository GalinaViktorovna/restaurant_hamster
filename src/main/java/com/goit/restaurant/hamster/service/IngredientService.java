package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.QuantityFilterDao;
import com.goit.restaurant.hamster.model.Ingredient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class IngredientService {
    private QuantityFilterDao<Ingredient> ingredientDao;

    @Transactional
    public void save(Ingredient ingredient){
        ingredientDao.save(ingredient);
    }

    @Transactional
    public void delete(long id){
        ingredientDao.delete(id);
    }

    @Transactional
    public List<Ingredient> getByName(String name){
        return ingredientDao.getByName(name);
    }

    @Transactional
    public Ingredient getById(long id){
        return ingredientDao.getById(id);
    }

    @Transactional
    public List<Ingredient> getAll(){
        return ingredientDao.getAll();
    }

    @Transactional
    public List<Ingredient> getEnding(){
        return ingredientDao.getEnding();
    }

    public void setIngredientDao(QuantityFilterDao<Ingredient> ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

}
