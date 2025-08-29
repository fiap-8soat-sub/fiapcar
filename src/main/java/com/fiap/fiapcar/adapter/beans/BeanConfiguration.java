package com.fiap.fiapcar.adapter.beans;

import com.fiap.fiapcar.application.model.CustomerDTO;
import com.fiap.fiapcar.application.ports.in.*;
import com.fiap.fiapcar.application.ports.out.*;
import com.fiap.fiapcar.application.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
public class BeanConfiguration {

    @Bean
    public BrandPort brandPort(
            BrandDatabasePort brandDatabasePort) {
        return new BrandPortImpl(brandDatabasePort);
    }

    @Bean
    public CarPort carPort(
            CarDatabasePort carDatabasePort) {
        return new CarPortImpl(carDatabasePort);
    }

    @Bean
    public SalePort salePort(
            SaleDatabasePort saleDatabasePort,
            CarDatabasePort carDatabasePort) {
        return new SalePortImpl(carDatabasePort, saleDatabasePort);
    }

    @Bean
    public CognitoAuthPort cognitoAuthPort(
            CognitoIdentityProviderClient cognitoIdentityProviderClient,
            CognitoProperties  props) {
        return new CognitoAuthPortImpl(cognitoIdentityProviderClient, props);
    }

    @Bean
    public CustomerPort customerPort(
            CustomerCognitoPort customerCognitoPort,
            CustomerDatabasePort customerDatabasePort) {
        return new CustomerPortImpl(customerCognitoPort, customerDatabasePort);
    }
}
