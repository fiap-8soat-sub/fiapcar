package com.fiap.fiapcar.application.ports.out;

import com.fiap.fiapcar.application.model.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerDatabasePort {

    Page<CustomerDTO> getAllCustomers(Pageable pageable);

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO createUser(CustomerDTO customerDTO);
}
