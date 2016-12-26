package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Ingredient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;
    
    @Before
    public void setUp() throws Exception {
        List<Ingredient> all = ingredientService.getAll();
        all.stream().forEach(e -> ingredientService.delete(e.getId()));

        Ingredient ingredient = new Ingredient();
        ingredient.setName("test ingredient");
        ingredient.setQuantity(4);
        ingredientService.save(ingredient);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("test ingredient 2");
        ingredient2.setQuantity(40);
        ingredientService.save(ingredient2);
    }

    @Test
    public void save() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("test ingredient 3");
        ingredientService.save(ingredient);

        List<Ingredient> all = ingredientService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        ingredientService.delete(2L);

        List<Ingredient> all = ingredientService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getByName() throws Exception {
        List<Ingredient> all = ingredientService.getByName("test ingredient");
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Ingredient> all = ingredientService.getAll();
        Ingredient ingredient = all.get(0);
        String expectedName = ingredient.getName();

        ingredient = ingredientService.getById(ingredient.getId());

        Assert.assertEquals(ingredient.getName(), expectedName);
    }

    @Test
    public void getAll() throws Exception {
        List<Ingredient> all = ingredientService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getEnding() throws Exception {
        List<Ingredient> all = ingredientService.getEnding();
        Assert.assertEquals(all.size(), 1);
    }

}