package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.AuthResponse;
import com.fiap.fiapcar.application.model.AuthDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthResponse toResponseFromDTO(AuthDTO authDTO);
}
