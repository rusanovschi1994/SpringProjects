package com.rusanovschi.shop.beautifulnails.entity;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @Column(name = "second_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String secondName;

    @Column(name = "username")
    @Email(message = "Email is not valid")
    private String username;

    @Column(name = "password")
    @Size(min = 3, message = "Password should have 3 minim characters")
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public User() {
    }

    public User(Integer id,
                String firstName,
                String secondName,
                String username,
                String password,
                boolean isEnabled,
                Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", createdAt=" + createdAt +
                '}';
    }
}
