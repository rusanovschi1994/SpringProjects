package com.rusanovschi.shop.beautifulnails.entity;

public class Service {

    private Integer id;

    private String name;

    private Integer price;

    private Integer duration;

    private boolean enabled;

    public Service() {
    }

    public Service(Integer id, String name, Integer price, Integer duration, boolean enabled) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
