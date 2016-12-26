package com.goit.restaurant.hamster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kitchen_journal")
public class KitchenJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "date")
    private String date = String.valueOf(new Date());

    @ManyToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfo orderInfo;

    @ManyToOne
    @JoinColumn(name = "cook")
    private Employee cook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Employee cook) {
        this.cook = cook;
    }
}
