package com.fiap.fiapcar.application.ports.in;

import com.fiap.fiapcar.application.model.CustomerDTO;

public interface CustomerPort {

    CustomerDTO create(CustomerDTO customerDTO);
}
