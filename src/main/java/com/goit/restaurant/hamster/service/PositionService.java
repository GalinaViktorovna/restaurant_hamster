package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Position;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PositionService {
    private DAO<Position> positionDao;

    @Transactional
    public void save(Position position){
        positionDao.save(position);
    }

    @Transactional
    public void delete(long id){
        positionDao.delete(id);
    }

    @Transactional
    public List<Position> getAll(){
        return positionDao.getAll();
    }

    @Transactional
    public Position getById(long id){
        return positionDao.getById(id);
    }

    public void setPositionDao(DAO<Position> positionDao) {
        this.positionDao = positionDao;
    }

}
