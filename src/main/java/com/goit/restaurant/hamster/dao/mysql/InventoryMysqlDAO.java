package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.dao.QuantityFilterDao;
import com.goit.restaurant.hamster.model.Ingredient;
import com.goit.restaurant.hamster.model.InventoryPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryMysqlDAO implements QuantityFilterDao<InventoryPosition>, MysqlDAO<InventoryPosition> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Ingredient> ingredientDao;

    @Override
    public void save(InventoryPosition inventoryPosition) {
        if(inventoryPosition.getId() == 0) {
            dataManager.create("INSERT INTO warehouse (ingredient_id, quantity) VALUES (?, ?)", inventoryPosition, this);
        } else {
            dataManager.update("UPDATE warehouse SET ingredient_id = ?, quantity = ? WHERE id = " + inventoryPosition.getId(), inventoryPosition, this);
        }
    }

    @Override
    public void delete(Long id) {
        dataManager.execute("DELETE FROM warehouse WHERE ID = ?", id);
    }

    @Override
    public InventoryPosition getById(Long id) {
        return dataManager.getById( "SELECT id, ingredient_id, quantity FROM warehouse WHERE id = ?", id, this);
    }

    @Override
    public List<InventoryPosition> getByName(String name) {
        return dataManager.getAll("SELECT warehouse.id, warehouse.ingredient_id, warehouse.quantity FROM warehouse INNER JOIN ingredient" +
                " ON warehouse.ingredient_id = ingredient.id WHERE ingredient.name LIKE '%" + name + "%'", this);
    }

    @Override
    public List<InventoryPosition> getAll() {
        return dataManager.getAll("SELECT id, ingredient_id, quantity FROM warehouse", this);
    }

    @Override
    public List<InventoryPosition> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public InventoryPosition getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        InventoryPosition inventoryPosition = new InventoryPosition();

        inventoryPosition.setId(resultSet.getLong("id"));
        inventoryPosition.setIngredient(ingredientDao.getById(resultSet.getLong("ingredient_id")));
        inventoryPosition.setQuantity(resultSet.getInt("quantity"));

        return inventoryPosition;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, InventoryPosition value) throws SQLException {
        preparedStatement.setLong(1, value.getIngredient().getId());
        preparedStatement.setInt(2, value.getQuantity());
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, InventoryPosition value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setIngredientDao(MysqlDAO<Ingredient> ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public List<InventoryPosition> getEnding() {
        return dataManager.getAll("SELECT id, ingredient_id, quantity FROM warehouse WHERE quantity < 10", this);
    }
}
