package com.rusanovschi.shop.beautifulnails.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Name should not be empty ")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @Column(name = "second_name", nullable = false)
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String secondName;

    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "Phone number should not be empty")
//    @Min(value = 0, message = "Phone number should be greater than zero")
    private String phoneNumber;

    @Column(name = "email", unique = true)
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "birth_date")
    @NotEmpty(message = "Date of birthday should not be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Transient
    private Integer age;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
                mappedBy = "customer")
    private List<Order> orders;

    public Customer() {

    }

    public Customer(Integer id,
                    String firstName,
                    String secondName,
                    String phoneNumber,
                    String email,
                    LocalDate dob,
                    Integer age,
                    boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.enabled = enabled;
    }

    public void addOrderToCustomer(Order order){

        if(orders == null){
            orders = new ArrayList<>();
        }

        orders.add(order);
        order.setCustomer(this);
    }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge()
    {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
