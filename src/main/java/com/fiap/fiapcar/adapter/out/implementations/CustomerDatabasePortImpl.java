package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.CustomerMapper;
import com.fiap.fiapcar.adapter.out.repository.CustomerRepository;
import com.fiap.fiapcar.application.model.CustomerDTO;
import com.fiap.fiapcar.application.ports.out.CustomerDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerDatabasePortImpl implements CustomerDatabasePort {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;


    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        log.info("[CustomerDatabasePortImpl.getAllBrands] Request");
        return customerRepository.findAll(pageable).map(customerMapper::toDTOFromEntity);
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        log.info("[CustomerDatabasePortImpl.getBrandById] Request");
        return customerMapper.toDTOFromEntity(customerRepository.findById(id).orElse(null));
    }

    @Override
    public CustomerDTO createUser(CustomerDTO customerDTO) {
        log.info("[CustomerDatabasePortImpl.createUser]");
        return customerMapper.toDTOFromEntity(
                customerRepository.save(
                        customerMapper.toEntityFromDTO(customerDTO)));
    }
}
