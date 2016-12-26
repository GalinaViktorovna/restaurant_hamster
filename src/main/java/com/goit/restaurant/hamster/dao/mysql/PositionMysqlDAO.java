package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionMysqlDAO implements MysqlDAO<Position> {

    private MysqlDataManager dataManager;

    @Override
    public Position getById(Long id) {
        return dataManager.getById("SELECT id, name FROM position WHERE id = ?", id, this);
    }

    @Override
    public List<Position> getAll() {
         return dataManager.getAll("SELECT id, name FROM position", this);
    }

    @Override
    public List<Position> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public Position getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Position position = new Position();

        position.setId(resultSet.getLong("id"));
        position.setName(resultSet.getString("name"));

        return position;
    }

    @Override
    public void save(Position value) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Position> getByName(String name) {
        return null;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, Position value) throws SQLException {

    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, Position value) throws SQLException {

    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }
}
