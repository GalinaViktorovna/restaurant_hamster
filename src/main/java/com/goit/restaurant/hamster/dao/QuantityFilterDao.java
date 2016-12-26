package com.goit.restaurant.hamster.dao;

import java.util.List;

public interface QuantityFilterDao<T> extends DAO<T> {
    List<T> getEnding();
}
