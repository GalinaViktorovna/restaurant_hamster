package com.goit.restaurant.hamster.dao.mysql;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDataManager {

    private DataSource dataSource;

    public <T> List<T> getAll(String sql, MysqlDAO<T> mysqlDAO){

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(mysqlDAO.getObjectFromResultSet(resultSet));
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T getById(String sql, long id, MysqlDAO<T> mysqlDAO){
        T result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                result = mysqlDAO.getObjectFromResultSet(resultSet);
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean execute(String sql, long id){
        boolean result = true;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public <T> void create(String sql, T value, MysqlDAO<T> mysqlDAO){

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            mysqlDAO.setStatementParamsFromObject(ps, value);
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mysqlDAO.setIdFromGeneratedKeys(generatedKeys, value);
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public <T> void update(String sql, T value, MysqlDAO<T> mysqlDAO){

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            mysqlDAO.setStatementParamsFromObject(ps, value);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
