package com.fiap.fiapcar.adapter.mappers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.SaleRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.SaleResponse;
import com.fiap.fiapcar.adapter.out.repository.entity.SaleEntity;
import com.fiap.fiapcar.application.model.NewSaleDTO;
import com.fiap.fiapcar.application.model.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CarMapper.class })
public interface SaleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    SaleDTO toDTOFromRequest(SaleRequest request);

    @Mapping(source = "car", target = "car")
    SaleResponse toResponseFromNewDTO(NewSaleDTO newSaleDTO);

    SaleResponse toResponseFromDTO(SaleDTO saleDTO);

    SaleDTO toDTOFromEntity(SaleEntity entity);

    SaleEntity toEntityFromDTO(SaleDTO dto);

}
