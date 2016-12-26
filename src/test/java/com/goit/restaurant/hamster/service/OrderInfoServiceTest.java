package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.OrderInfo;
import com.goit.restaurant.hamster.model.OrderPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
public class OrderInfoServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderInfoService orderInfoService;
    
    @Before
    public void setUp() throws Exception {
        List<OrderInfo> all = orderInfoService.getAll();
        all.stream().forEach(e -> orderInfoService.delete(e.getId()));

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setClosed(true);
        orderInfoService.save(orderInfo);

        OrderPosition position = new OrderPosition();
        position.setOrderInfo(orderInfo);
        position.setDish(dish);
        position.setQuantity(10);

        List<OrderPosition> positions = new ArrayList<>();
        positions.add(position);
        orderInfo.setOrderContent(positions);
        orderInfoService.save(orderInfo);

        OrderInfo orderInfo2 = new OrderInfo();
        orderInfo2.setClosed(false);
        orderInfoService.save(orderInfo2);
    }

    @Test
    public void save() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setClosed(false);
        orderInfoService.save(order);

        List<OrderInfo> all = orderInfoService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        orderInfoService.delete(2L);

        List<OrderInfo> all = orderInfoService.getAll();
        Assert.assertEquals(all.size(), 1);
    }
    
    @Test
    public void getById() throws Exception {
        List<OrderInfo> all = orderInfoService.getAll();
        OrderInfo orderInfo = all.get(0);
        String expectedDate = orderInfo.getDate();

        orderInfo = orderInfoService.getById(orderInfo.getId());

        Assert.assertEquals(orderInfo.getDate(), expectedDate);
    }

    @Test
    public void getAll() throws Exception {
        List<OrderInfo> all = orderInfoService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    @Ignore
    public void getOpen() throws Exception {
        List<OrderInfo> all = orderInfoService.getOpen();
        Assert.assertEquals(all.size(), 1);
    }
}