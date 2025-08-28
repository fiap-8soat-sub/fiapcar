package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import com.fiap.fiapcar.application.model.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    CarDTO toDTOFromRequest(CarRequest request);

    CarResponse toResponseFromDTO(CarDTO carDTO);

    CarDTO toDTOFromEntity(CarEntity entity);

    CarEntity toEntityFromDTO(CarDTO dto);
}
