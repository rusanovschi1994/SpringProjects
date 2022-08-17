package com.rusanovschi.shop.beautifulnails;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopBeautifulNailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBeautifulNailsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
