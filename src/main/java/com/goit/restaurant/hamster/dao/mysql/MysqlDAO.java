package com.goit.restaurant.hamster.dao.mysql;

import com.goit.restaurant.hamster.dao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

;

public interface MysqlDAO<T> extends DAO<T> {

    T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    void setStatementParamsFromObject(PreparedStatement preparedStatement, T value) throws SQLException;

    void setIdFromGeneratedKeys(ResultSet generatedKeys, T value) throws SQLException;

    List<T> getFromQuery(String sql);
}
