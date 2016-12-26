package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.OrderPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderPositionMysqlDAO implements MysqlDAO<OrderPosition> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Dish> dishDao;

    @Override
    public void save(OrderPosition orderPosition) {
        if (orderPosition.getId() == 0) {
            dataManager.create("INSERT INTO order_content (order_info_id, dish_id, quantity) VALUES (?, ?, ?)", orderPosition, this);
        } else {
            dataManager.update("UPDATE order_content SET order_info_id = ?, dish_id = ?, quantity = ?  WHERE id = " + orderPosition.getId(), orderPosition, this);
        }
    }

    @Override
    public void delete(Long id) {
        dataManager.execute("DELETE FROM order_content WHERE id = ?", id);
    }

    @Override
    public OrderPosition getById(Long id) {
        return dataManager.getById("SELECT id, order_info_id,dish_id, quantity FROM order_content WHERE id = ?", id, this);
    }

    @Override
    public List<OrderPosition> getByName(String name) {
        return null;
    }

    @Override
    public List<OrderPosition> getAll() {
        return dataManager.getAll("SELECT id, order_info_id, dish_id, quantity FROM order_content", this);
    }

    @Override
    public List<OrderPosition> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public OrderPosition getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        OrderPosition orderPosition = new OrderPosition();

        orderPosition.setId(resultSet.getLong("ID"));
        //orderPosition.setOrder(resultSet.getLong("order_info_id"));
        orderPosition.setDish(dishDao.getById(resultSet.getLong("dish_id")));
        orderPosition.setQuantity(resultSet.getInt("quantity"));

        return orderPosition;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, OrderPosition value) throws SQLException {
        preparedStatement.setLong(1, value.getOrderInfo().getId());
        preparedStatement.setLong(2, value.getDish().getId());
        preparedStatement.setInt(3, value.getQuantity());
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, OrderPosition value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setDishDao(MysqlDAO<Dish> dishDao) {
        this.dishDao = dishDao;
    }
}
