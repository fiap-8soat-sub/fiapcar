package com.fiap.fiapcar.adapter.beans;

import com.fiap.fiapcar.application.ports.in.BrandPort;
import com.fiap.fiapcar.application.ports.in.CarPort;
import com.fiap.fiapcar.application.ports.in.SalePort;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import com.fiap.fiapcar.application.ports.out.SaleDatabasePort;
import com.fiap.fiapcar.application.services.BrandPortImpl;
import com.fiap.fiapcar.application.services.CarPortImpl;
import com.fiap.fiapcar.application.services.SalePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BrandPort brandPort(BrandDatabasePort brandDatabasePort) {
        return new BrandPortImpl(brandDatabasePort);
    }

    @Bean
    public CarPort carPort(CarDatabasePort carDatabasePort) {
        return new CarPortImpl(carDatabasePort);
    }

    @Bean
    public SalePort salePort(SaleDatabasePort saleDatabasePort, CarDatabasePort carDatabasePort) {
        return new SalePortImpl(carDatabasePort, saleDatabasePort);
    }
}
