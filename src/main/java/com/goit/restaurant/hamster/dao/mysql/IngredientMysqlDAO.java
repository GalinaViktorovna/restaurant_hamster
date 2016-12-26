package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IngredientMysqlDAO implements MysqlDAO<Ingredient> {

    private MysqlDataManager dataManager;

    @Override
    public void save(Ingredient value) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Ingredient getById(Long id) {
        return dataManager.getById("SELECT id, name FROM ingredient WHERE id = ?", id, this);
    }

    @Override
    public List<Ingredient> getByName(String name) {
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        return dataManager.getAll("SELECT id, name FROM ingredient", this);
    }

    @Override
    public List<Ingredient> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public Ingredient getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();

        ingredient.setId(resultSet.getLong("id"));
        ingredient.setName(resultSet.getString("name"));

        return ingredient;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, Ingredient value) throws SQLException {

    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, Ingredient value) throws SQLException {

    }


    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

}
