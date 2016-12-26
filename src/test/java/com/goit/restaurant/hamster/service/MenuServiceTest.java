package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.Menu;
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
public class MenuServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuService menuService;

    @Before
    public void setUp() throws Exception {
        List<Menu> all = menuService.getAll();
        all.stream().forEach(e -> menuService.delete(e.getId()));

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        Menu menu = new Menu();
        menu.setName("test menu");
        menuService.save(menu);

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        menu.setDishes(dishes);
        menuService.save(menu);

        Menu menu2 = new Menu();
        menu2.setName("test menu 2");
        menuService.save(menu2);
    }

    @Test
    public void save() throws Exception {
        Menu menu = new Menu();
        menu.setName("test menu");
        menuService.save(menu);

        List<Menu> all = menuService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        menuService.delete(2L);

        List<Menu> all = menuService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getByName() throws Exception {
        List<Menu> all = menuService.getByName("test");
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Menu> all = menuService.getAll();
        Menu menu = all.get(0);
        String expectedName = menu.getName();

        menu = menuService.getById(menu.getId());

        Assert.assertEquals(menu.getName(), expectedName);
    }

    @Test
    public void getAll() throws Exception {
        List<Menu> all = menuService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

}