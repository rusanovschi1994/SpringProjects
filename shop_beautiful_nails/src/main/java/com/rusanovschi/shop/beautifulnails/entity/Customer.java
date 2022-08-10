package com.rusanovschi.shop.beautifulnails.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
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
    private String phoneNumber;

    @Column(name = "email", unique = true)
    @Email(message = "Email is not valid")
    private String email;

//    @Column(name = "birth_date")
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private Date dob;
    @Column(name = "birth_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "address")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address should be in this format: Country, City, Postal Code(6 characters)")
    private String address;

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
                    String address,
                    Integer age,
                    Date createdAt,
                    boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.age = age;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {

        this.createdAt = createdAt;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
