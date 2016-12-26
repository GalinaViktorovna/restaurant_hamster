package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.DishComponent;
import com.goit.restaurant.hamster.model.Ingredient;
import com.goit.restaurant.hamster.model.KitchenJournal;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class KitchenJournalService {

    private DAO<KitchenJournal> kitchenJournalDao;
    private IngredientService ingredientService;
    private DishService dishService;

    @Transactional
    public void save(KitchenJournal kitchenJournal) {
        useIngredientsOnStock(kitchenJournal);
        kitchenJournalDao.save(kitchenJournal);
    }

    private void useIngredientsOnStock(KitchenJournal kitchenJournalItem) {
        boolean successful = true;
        StringBuilder errorMessage = new StringBuilder();
        List<Ingredient> stockToSave = new ArrayList<>();

        Dish dish = dishService.getById(kitchenJournalItem.getDish().getId());
        List<DishComponent> ingredientList = dish.getComponents();
        for (DishComponent dishComponent : ingredientList) {
            Ingredient ingredient = dishComponent.getIngredient();
            if (ingredient.getQuantity() < dishComponent.getQuantity()) {
                successful = false;
                errorMessage.append(ingredient.getName() + " is not available! \n");
            } else {
                ingredient.setQuantity(ingredient.getQuantity() - dishComponent.getQuantity());
                stockToSave.add(ingredient);
            }
        }
        if (successful) {
            stockToSave.forEach(e -> ingredientService.save(e));
        } else {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    @Transactional
    public void delete(long id) {
        kitchenJournalDao.delete(id);
    }

    @Transactional
    public List<KitchenJournal> getByName(String name) {
        return kitchenJournalDao.getByName(name);
    }

    @Transactional
    public KitchenJournal getById(long id) {
        return kitchenJournalDao.getById(id);
    }

    @Transactional
    public List<KitchenJournal> getAll() {
        return kitchenJournalDao.getAll();
    }

    public void setKitchenJournalDao(DAO<KitchenJournal> kitchenJournalItemDao) {
        this.kitchenJournalDao = kitchenJournalItemDao;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
