package com.quickbite.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_addresses")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserAddress() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
