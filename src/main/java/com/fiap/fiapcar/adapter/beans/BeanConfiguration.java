package com.fiap.fiapcar.adapter.beans;

import com.fiap.fiapcar.application.ports.in.BrandPort;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import com.fiap.fiapcar.application.services.BrandPortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BrandPort brandPort(BrandDatabasePort brandDatabasePort) {
        return new BrandPortImpl(brandDatabasePort);
    }
}
