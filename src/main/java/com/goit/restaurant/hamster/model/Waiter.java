package com.goit.restaurant.hamster.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Employee {
    @OneToMany(mappedBy = "waiter",
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<OrderInfo> orders;

    public List<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

}