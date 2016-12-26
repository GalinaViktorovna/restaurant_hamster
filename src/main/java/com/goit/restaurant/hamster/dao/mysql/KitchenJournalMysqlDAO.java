package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Dish;
import com.goit.restaurant.hamster.model.Employee;
import com.goit.restaurant.hamster.model.KitchenJournal;
import com.goit.restaurant.hamster.model.OrderInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class KitchenJournalMysqlDAO implements MysqlDAO<KitchenJournal> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Dish> dishDao;
    private MysqlDAO<Employee> employeeDao;
    private MysqlDAO<OrderInfo> orderInfoDao;

    @Override
    public void save(KitchenJournal kitchenJournal) {
        if (kitchenJournal.getId() == 0) {
            dataManager.create("INSERT INTO kitchen_journal (dish_id, cook_id, order_id, date) VALUES (?, ?, ?, ?)", kitchenJournal, this);
        } else {
            dataManager.update("UPDATE kitchen_journal SET dish_id = ?, cook_id = ?, order_id = ?, date = ? WHERE id = " + kitchenJournal.getId(), kitchenJournal, this);
        }
    }

    @Override
    public KitchenJournal getById(Long id) {
        return dataManager.getById("SELECT id, dish_id, cook_id, order_id, date FROM kitchen_journal WHERE id = ?", id, this);

    }

    @Override
    public void delete(Long id) {

        dataManager.execute("DELETE FROM kitchen_journal WHERE id = ?", id);
    }

    @Override
    public List<KitchenJournal> getByName(String name) {
        return null;
    }

    @Override
    public List<KitchenJournal> getAll() {
        return dataManager.getAll("SELECT id, dish_id, cook_id, order_info_id, date FROM kitchen_journal", this);

    }

    @Override
    public List<KitchenJournal> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public KitchenJournal getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        KitchenJournal kitchenJournal = new KitchenJournal();

        kitchenJournal.setId(resultSet.getLong("id"));
        kitchenJournal.setDate(resultSet.getString("date"));
        kitchenJournal.setDish(dishDao.getById(resultSet.getLong("dish_id")));
        kitchenJournal.setCook(null);
        kitchenJournal.setOrderInfo(orderInfoDao.getById(resultSet.getLong("order_info_id")));

        return kitchenJournal;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, KitchenJournal value) throws SQLException {
        preparedStatement.setLong(1, value.getDish().getId());
        preparedStatement.setLong(2, value.getCook().getId());
        preparedStatement.setLong(3, value.getOrderInfo().getId());
        preparedStatement.setString(4, value.getDate());
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, KitchenJournal value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }

    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setDishDao(MysqlDAO<Dish> dishDao) {
        this.dishDao = dishDao;
    }

    public void setEmployeeDao(MysqlDAO<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setOrderInfoDao(MysqlDAO<OrderInfo> orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }
}
