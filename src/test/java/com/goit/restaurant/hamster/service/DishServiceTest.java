package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Dish;
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
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Before
    public void setUp(){
        List<Dish> all = dishService.getAll();
        all.stream().forEach(e -> dishService.delete(e.getId()));

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        Dish dish2 = new Dish();
        dish2.setName("test dish 2");
        dishService.save(dish2);
    }

    @Test
    public void save() throws Exception {
        Dish dish = new Dish();
        dish.setName("test dish 3");
        dishService.save(dish);

        List<Dish> all = dishService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(2L);

        List<Dish> all = dishService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getByName() throws Exception {
        List<Dish> all = dishService.getByName("test dish");
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Dish> all = dishService.getAll();
        Dish dish = all.get(0);
        String expectedName = dish.getName();

        dish = dishService.getById(dish.getId());

        Assert.assertEquals(dish.getName(), expectedName);
    }

    @Test
    public void getAll() throws Exception {
        List<Dish> all = dishService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

}