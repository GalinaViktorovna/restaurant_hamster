package com.goit.restaurant.hamster.service;


import com.goit.restaurant.hamster.model.Position;
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
public class PositionServiceTest {
    
    @Autowired
    private PositionService positionService;
    
    @Before
    public void setUp() throws Exception {
        List<Position> all = positionService.getAll();
        all.stream().forEach(e -> positionService.delete(e.getId()));

        Position position = new Position();
        position.setName("test position");
        positionService.save(position);

        Position position2 = new Position();
        position2.setName("test position 2");
        positionService.save(position2);
    }

    @Test
    public void save() throws Exception {
        Position position = new Position();
        position.setName("test position 3");
        positionService.save(position);

        List<Position> all = positionService.getAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        positionService.delete(2L);

        List<Position> all = positionService.getAll();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getAll() throws Exception {
        List<Position> all = positionService.getAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Position> all = positionService.getAll();
        Position position = all.get(1);
        String expectedName = position.getName();

        position = positionService.getById(position.getId());

        Assert.assertEquals(position.getName(), expectedName);
    }

}