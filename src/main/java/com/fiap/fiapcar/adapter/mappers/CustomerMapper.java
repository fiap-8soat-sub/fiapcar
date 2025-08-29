package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CustomerRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CustomerResponse;
import com.fiap.fiapcar.application.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    CustomerDTO toDTOFromRequest(CustomerRequest request);


    CustomerResponse toResponseFromDTO(CustomerDTO brandDTO);
}
