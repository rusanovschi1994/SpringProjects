package com.rusanovschi.shop.beautifulnails.entity;

import java.time.LocalDate;
import java.time.LocalTime;


public class Order {

    private Integer id;

    private Integer customerID;

    private Integer serviceID;

    private LocalDate orderDate;

    private LocalTime orderTime;

    private String description;

    private Short status;

    public Order() {
    }

    public Order(Integer customerID,
                 Integer serviceID,
                 LocalDate orderDate,
                 LocalTime orderTime,
                 String description,
                 Short status) {
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
