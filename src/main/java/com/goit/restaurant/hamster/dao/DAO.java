package com.goit.restaurant.hamster.dao;

import java.util.List;

public interface DAO<T> {

    void save(T value);

    void delete(Long id);

    T getById(Long id);

    List<T> getByName(String name);

    List<T> getAll();
}
