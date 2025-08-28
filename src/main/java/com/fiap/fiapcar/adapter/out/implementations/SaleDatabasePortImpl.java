package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.SaleMapper;
import com.fiap.fiapcar.adapter.out.repository.SaleRepository;
import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import com.fiap.fiapcar.adapter.out.repository.entity.SaleEntity;
import com.fiap.fiapcar.application.model.SaleDTO;
import com.fiap.fiapcar.application.ports.out.SaleDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaleDatabasePortImpl implements SaleDatabasePort {

    private final SaleMapper saleMapper;
    private final SaleRepository saleRepository;


    @Override
    public SaleDTO createSale(SaleDTO saleDTO) {
        log.info("[SaleDatabasePortImpl.createSale]");
        return saleMapper.toDTOFromEntity(
                saleRepository.save(
                        saleMapper.toEntityFromDTO(saleDTO)
                ));
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        log.info("[SaleDatabasePortImpl.getSaleById] Request");
        Optional<SaleEntity> saleEntity = saleRepository.findBySaleId(id);
        return saleMapper.toDTOFromEntity(saleEntity.orElse(null));
    }

    @Override
    public Page<SaleDTO> getAllSales(Pageable pageable) {
        log.info("[SaleDatabasePortImpl.getAllSales] Request");
        return saleRepository.findAll(pageable).map(saleMapper::toDTOFromEntity);
    }

    @Override
    public   SaleDTO finalizeSale(SaleDTO saleDTO, Long id) {
        return saleMapper.toDTOFromEntity(
                saleRepository.save(
                        saleMapper.toEntityFromDTO(saleDTO)
                ));
    }

    @Override
    public boolean getSaleByCarId(Long carId) {
        return saleRepository.existsByCarId(carId);
    }
}
