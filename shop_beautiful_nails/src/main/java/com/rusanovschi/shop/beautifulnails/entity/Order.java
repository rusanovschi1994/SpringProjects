package com.rusanovschi.shop.beautifulnails.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_time")
    private LocalTime orderTime;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Short status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = 'order_service',
            joinColumns = @JoinColumn("order_id"),
            inverseJoinColumns = @JoinColumn("service_id")
    )
    private List<Service> services;

    public Order() {
    }

}
