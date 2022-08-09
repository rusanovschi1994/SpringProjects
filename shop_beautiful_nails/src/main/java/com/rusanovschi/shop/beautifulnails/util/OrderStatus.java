package com.rusanovschi.shop.beautifulnails.util;

public enum OrderStatus {

    New("New"), In_progress("In progress"), Completed("Completed"), Canceled("Canceled");

    OrderStatus(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus(){

        return this.status;
    }
}
