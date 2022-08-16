package com.rusanovschi.shop.beautifulnails.util.restError;

public class CustomerNotCreatedException extends RuntimeException{

    public CustomerNotCreatedException(String eMsg){
        super(eMsg);
    }
}
