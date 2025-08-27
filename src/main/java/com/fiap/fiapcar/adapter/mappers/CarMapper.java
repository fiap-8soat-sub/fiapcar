package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import com.fiap.fiapcar.application.model.CarDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO toDTOFromRequest(CarRequest request);

    // DTO -> Response
    CarResponse toResponseFromDTO(CarDTO brandDTO);

    // Entity -> DTO
    CarDTO toDTOFromEntity(CarEntity entity);

    // DTO -> Entity
    CarEntity toEntityFromDTO(CarDTO dto);

    // Listas
    List<CarResponse> toResponseFromDTOList(List<CarDTO> dtoList);
    List<CarDTO> toDTOFromEntityList(List<CarEntity> entityList);
}
