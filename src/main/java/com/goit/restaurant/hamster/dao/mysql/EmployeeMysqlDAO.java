package com.goit.restaurant.hamster.dao.mysql;


import com.goit.restaurant.hamster.model.Employee;
import com.goit.restaurant.hamster.model.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeMysqlDAO implements MysqlDAO<Employee> {

    private MysqlDataManager dataManager;
    private MysqlDAO<Position> positionDAO;

    @Override
    public void save(Employee employee) {
        if(employee.getId() == 0) {
            dataManager.create("INSERT INTO employee (name,age,position_id) VALUES (?, ?, ?)", employee, this);
        } else{
            dataManager.update("UPDATE employee SET name = ?, age = ?, position_id = ? WHERE id = " + employee.getId(), employee, this);
        }
    }

    @Override
    public void delete(Long id) {
        dataManager.execute("DELETE FROM employee WHERE id = ?", id);
    }

    @Override
    public Employee getById(Long id) {
        return dataManager.getById( "SELECT id, name, age, position_id FROM employee WHERE id = ?", id, this);
    }

    @Override
    public List<Employee> getByName(String name) {
        return dataManager.getAll("SELECT id, name, age, position_id FROM employee WHERE name LIKE '%" + name + "%'", this);
    }

    @Override
    public List<Employee> getAll() {
        return dataManager.getAll("SELECT id, name, age, position_id FROM employee", this);
    }

    @Override
    public List<Employee> getFromQuery(String sql) {
        return dataManager.getAll(sql, this);
    }

    @Override
    public Employee getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getLong("id"));
        employee.setName(resultSet.getString("name"));
        employee.setAge(resultSet.getInt("age"));
        employee.setPosition(positionDAO.getById(resultSet.getLong("position_id")));

        return employee;
    }

    @Override
    public void setStatementParamsFromObject(PreparedStatement preparedStatement, Employee value) throws SQLException {
        preparedStatement.setString(1, value.getName());
        preparedStatement.setInt(2, value.getAge());
        if (value.getPosition() != null) {
            preparedStatement.setLong(3, value.getPosition().getId());
        }
    }

    @Override
    public void setIdFromGeneratedKeys(ResultSet generatedKeys, Employee value) throws SQLException {
        value.setId(generatedKeys.getLong(1));
    }


    public void setDataManager(MysqlDataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void setPositionDAO(MysqlDAO<Position> positionDAO) {
        this.positionDAO = positionDAO;
    }
}
