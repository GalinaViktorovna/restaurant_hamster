package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.DishComponent;
import com.goit.restaurant.hamster.model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishMysqlDAO implements MysqlDAO<Dish> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Ingredient> ingredientDAO;

    @Override
    public void save(Dish dish) {
        if (dish.getId() == 0) {
            dataManager.create("INSERT INTO dish (name) VALUES (?)", dish, this);
        } else {
            dataManager.update("UPDATE dish SET NAME = ? WHERE ID = " + dish.getId(), dish, this);
        }
        setDishComponents(dish);
    }

    @Override
    public void delete(Long id) {
        deleteDishComponents(id);
        dataManager.execute("DELETE FROM dish WHERE id = ?", id);

    }

    @Override
    public Dish getById(Long id) {
        return dataManager.getById("SELECT id, name FROM dish WHERE id = ?", id, this);

    }

    @Override
    public List<Dish> getByName(String name) {
        return dataManager.getAll("SELECT id, NAME FROM dish WHERE name LIKE '%" + name + "%'", this);
    }

    @Override
    public List<Dish> getAll() {
        return dataManager.getAll("SELECT id, NAME FROM dish", this);
    }

    @Override
    public List<Dish> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    private List<Ingredient> getDishComponents(long dishId) {
        String sql = "select id, name " +
                "FROM ingredient where id in " +
                "(select ingredient_id from dish_components " +
                "where dish_id = " + dishId + ")";

        return ingredientDAO.getFromQuery(sql);
    }

    private void setDishComponents(Dish dish) {
        deleteDishComponents(dish.getId());

        if (dish.getComponents() != null) {
            for (DishComponent dishComponent : dish.getComponents()) {
                dataManager.execute("INSERT INTO dish_components(dish_id, ingredient_id) VALUES ("
                        + "? , " + dishComponent.getId() + ")", dish.getId());
            }
        }
    }

    private void deleteDishComponents(long dishId) {
        dataManager.execute("DELETE FROM dish_components WHERE dish_id = ?", dishId);
    }

    @Override
    public Dish getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();

        dish.setId(resultSet.getLong("id"));
        dish.setName(resultSet.getString("name"));
        //dish.setComponents(getComponents(dish.getId()));

        return dish;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, Dish value) throws SQLException {
        preparedStatement.setString(1, value.getName());
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, Dish value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setIngredientDAO(MysqlDAO<Ingredient> ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }
}
