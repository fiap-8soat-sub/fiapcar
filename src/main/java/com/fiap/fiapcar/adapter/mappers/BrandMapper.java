package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.BrandRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.out.repository.entity.BrandEntity;
import com.fiap.fiapcar.application.model.BrandDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    // Request -> DTO
    BrandDTO toDTOFromRequest(BrandRequest request);

    // DTO -> Response
    BrandResponse toResponseFromDTO(BrandDTO brandDTO);

    // Entity -> DTO
    BrandDTO toDTOFromEntity(BrandEntity entity);

    // DTO -> Entity
    BrandEntity toEntityFromDTO(BrandDTO dto);

    // Listas
    List<BrandResponse> toResponseFromDTOList(List<BrandDTO> dtoList);
    List<BrandDTO> toDTOFromEntityList(List<BrandEntity> entityList);
}
