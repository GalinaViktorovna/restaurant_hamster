package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.DishComponent;
import com.goit.restaurant.hamster.model.Ingredient;
import com.goit.restaurant.hamster.model.KitchenJournal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class KitchenJournalServiceTest {

    @Autowired
    private KitchenJournalService kitchenJournalService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private DishService dishService;

    @Before
    public void setUp() throws Exception {
        List<KitchenJournal> all = kitchenJournalService.getAll();
        all.stream().forEach(e -> kitchenJournalService.delete(e.getId()));

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        KitchenJournal kitchenItem = new KitchenJournal();
        kitchenItem.setDish(dish);
        kitchenJournalService.save(kitchenItem);
    }

    @Test
    public void save() throws Exception {
        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        KitchenJournal kitchenItem = new KitchenJournal();
        kitchenItem.setDish(dish);
        kitchenJournalService.save(kitchenItem);

        List<KitchenJournal> all = kitchenJournalService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNoIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("test ingredient");
        ingredient.setQuantity(2);
        ingredientService.save(ingredient);

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        DishComponent component = new DishComponent();
        component.setDish(dish);
        component.setIngredient(ingredient);
        component.setQuantity(3);

        List<DishComponent> components = new ArrayList<>();
        components.add(component);
        dish.setComponents(components);
        dishService.save(dish);

        KitchenJournal kitchenItem = new KitchenJournal();
        kitchenItem.setDish(dish);
        kitchenJournalService.save(kitchenItem);
    }

    @Test
    public void delete() throws Exception {
        kitchenJournalService.delete(1L);

        List<KitchenJournal> all = kitchenJournalService.getAll();
        Assert.assertEquals(all.size(), 0);
    }

    @Test
    public void getById() throws Exception {
        List<KitchenJournal> all = kitchenJournalService.getAll();
        KitchenJournal kitchenItem = all.get(0);
        String expectedDate = kitchenItem.getDate();

        kitchenItem = kitchenJournalService.getById(kitchenItem.getId());

        Assert.assertEquals(kitchenItem.getDate(), expectedDate);
    }

    @Test
    public void getAll() throws Exception {
        List<KitchenJournal> all = kitchenJournalService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

}