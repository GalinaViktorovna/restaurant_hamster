package com.goit.restaurant.hamster.dao.mysql;

import com.goit.restaurant.hamster.model.*;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMysqlDAO implements MysqlDAO<OrderInfo> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Employee> employeeDao;
    private MysqlDAO<OrderPosition> orderPositionDao;

    @Override
    public void save(OrderInfo orderInfo) {
        if (orderInfo.getId() == 0) {
            dataManager.create("INSERT INTO order_info (table_number, date, waiter, open) VALUES (?, ?, ?, ?)", orderInfo, this);
        } else {
            dataManager.update("UPDATE order_info SET table_number = ?, date = ?, waiter = ?, open = ?  WHERE id = " + orderInfo.getId(), orderInfo, this);
        }
        setContents(orderInfo);
    }

    @Override
    public void delete(Long id) {
        dataManager.execute("DELETE FROM order_info WHERE id = ?", id);
    }

    @Override
    public OrderInfo getById(Long id) {
        return dataManager.getById("SELECT id, table_number, date, waiter, open FROM order_info WHERE id = ?", id, this);
    }

    @Override
    public List<OrderInfo> getByName(String name) {
        return null;
    }

    @Override
    public List<OrderInfo> getAll() {
        return dataManager.getAll("SELECT id, table_number, date, waiter, open FROM order_info", this);
    }

    @Override
    public List<OrderInfo> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    private List<OrderPosition> getContents(OrderInfo orderInfo) {
        String sql = "SELECT id, dish_id, quantity " +
                "FROM order_content where order_info_id = " + orderInfo.getId();

        List<OrderPosition> orderPositions = orderPositionDao.getFromQuery(sql);
        for (OrderPosition orderPosition : orderPositions) {
            orderPosition.setOrderInfo(orderInfo);
        }

        return orderPositions;
    }

    private void setContents(OrderInfo orderInfo) {
        deleteContents(orderInfo.getId());

        if (orderInfo.getOrderContent() != null) {
            for (OrderPosition orderPosition : orderInfo.getOrderContent()) {
                orderPosition.setOrderInfo(orderInfo);
                orderPositionDao.save(orderPosition);
            }
        }
    }

    private void deleteContents(long orderId) {
        dataManager.execute("DELETE FROM order_content WHERE order_info_id = ?", orderId);
    }

    @Override
    public OrderInfo getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setId(resultSet.getLong("id"));
        orderInfo.setTableNumber(resultSet.getInt("table_number"));
        orderInfo.setDate(resultSet.getString("date"));
        //orderInfo.setWaiter(employeeDao.getById(resultSet.getLong("WAITER")));
        if (resultSet.getInt("open") == 1) {
            orderInfo.setClosed(true);
        }

        orderInfo.setOrderContent(getContents(orderInfo));

        return orderInfo;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, OrderInfo value) throws SQLException {
        preparedStatement.setInt(1, value.getTableNumber());
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.setLong(3, value.getWaiter().getId());
        preparedStatement.setInt(4, value.getClosed() ? 1 : 0);
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, OrderInfo value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }


    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setEmployeeDao(MysqlDAO<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setOrderPositionDao(MysqlDAO<OrderPosition> orderPositionDao) {
        this.orderPositionDao = orderPositionDao;
    }
}
