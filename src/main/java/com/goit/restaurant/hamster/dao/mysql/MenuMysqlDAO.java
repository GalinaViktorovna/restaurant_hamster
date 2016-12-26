package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuMysqlDAO implements MysqlDAO<Menu> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Dish> dishDao;

    @Override
    public void save(Menu menu) {
        if (menu.getId() == 0) {
            dataManager.create("INSERT INTO menu (name) VALUES (?)", menu, this);
        } else {
            dataManager.update("UPDATE menu SET name = ? WHERE id = " + menu.getId(), menu, this);
        }
        setDishes(menu);
    }

    @Override
    public void delete(Long id) {
        dataManager.execute("DELETE FROM menu WHERE id = ?", id);
    }


    @Override
    public Menu getById(Long id) {
        return dataManager.getById("SELECT id, name FROM menu WHERE id = ?", id, this);
    }

    @Override
    public List<Menu> getByName(String name) {
        return dataManager.getAll("SELECT id, name FROM menu WHERE name LIKE '%" + name + "%'", this);
    }

    @Override
    public List<Menu> getAll() {
        return dataManager.getAll("SELECT id, name FROM menu", this);
    }

    @Override
    public List<Menu> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    private List<Dish> getDishes(long menuId) {

        String sql = "SELECT id, name " +
                "FROM dish where id in " +
                "(select dish_id from menu_content " +
                "where menu_id = " + menuId + ")";

        return dishDao.getFromQuery(sql);
    }

    private void setDishes(Menu menu) {
        deleteDishes(menu.getId());

        if (menu.getDishes() != null) {
            for (Dish dish : menu.getDishes()) {
                dataManager.execute("INSERT INTO menu_content(menu_id, dish_id) VALUES ("
                        + "?, " + dish.getId() + ")", menu.getId());
            }
        }
    }

    private void deleteDishes(long menuId) {
        dataManager.execute("DELETE FROM menu_content WHERE menu_id = ?", menuId);
    }

    @Override
    public Menu getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();

        menu.setId(resultSet.getLong("id"));
        menu.setName(resultSet.getString("name"));
        menu.setDishes(getDishes(menu.getId()));

        return menu;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, Menu value) throws SQLException {
        preparedStatement.setString(1, value.getName());
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, Menu value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setDishDao(MysqlDAO<Dish> dishDao) {
        this.dishDao = dishDao;
    }
}
