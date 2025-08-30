package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.model.NewSaleDTO;
import com.fiap.fiapcar.application.model.SaleDTO;
import com.fiap.fiapcar.application.ports.in.SalePort;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import com.fiap.fiapcar.application.ports.out.SaleDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SalePortImpl implements SalePort {

    private final CarDatabasePort carDatabasePort;
    private final SaleDatabasePort saleDatabasePort;

    @Override
    public NewSaleDTO createSale(SaleDTO saleDTO) {
        log.info("[SalePortImpl.createSale]");
        CarDTO car = Optional.ofNullable(carDatabasePort.getCarById(saleDTO.getCarId()))
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if(saleDatabasePort.getSaleByCarId(car.getId())){
            log.info("[Car already have a sale");
            throw new RuntimeException("Car already have a sale");
        }

        if(car.getStatus().equals("PAID") || car.getStatus().equals("RESERVED")) {
            log.info("[Car status not accept new sale] carStatus: {}", car.getStatus());
            throw new RuntimeException("Car status not accept new sale");
        }

        if(saleDTO.getAmountPaid() < car.getPrice()/2){
            log.info("[Amount paid is not enough!] CarValue: {}", car.getPrice());
            throw new RuntimeException("Amount paid is not enough");
        }
        if(saleDTO.getAmountPaid() < car.getPrice()) {
            log.info("[Total amount not paid, car will be reserved]");
            saleDTO.setStatus("RESERVED");
            car.setStatus("RESERVED");
        }else{
            saleDTO.setStatus("PAID");
            car.setStatus("PAID");
        }

        saleDTO.setCreatedAt(LocalDateTime.now());
        saleDTO.setUpdatedAt(saleDTO.getCreatedAt());
        SaleDTO sale = saleDatabasePort.createSale(saleDTO);

        carDatabasePort.updateStatusCarByid(sale.getCarId(), car.getStatus());
        return buildNewSale(sale, car);
    }

    @Override
    public NewSaleDTO getSaleById(Long id) {
        log.info("[SalePortImpl.getSaleById]");
        SaleDTO sale = saleDatabasePort.getSaleById(id);
        CarDTO car  = carDatabasePort.getCarById(sale.getCarId());
        return buildNewSale(sale, car);
    }

    @Override
    public Page<SaleDTO> getAllSales(Pageable pageable) {
        log.info("[SalePortImpl.getAllSales]");
        return saleDatabasePort.getAllSales(pageable);
    }

    @Override
    public SaleDTO finalizeSale(SaleDTO saleDTO, Long id) {
        log.info("[SalePortImpl.finalizeSale]");
        CarDTO car = Optional.ofNullable(carDatabasePort.getCarById(saleDTO.getCarId()))
                .orElseThrow(() -> new RuntimeException("Car not found"));

        SaleDTO sale = Optional.ofNullable(saleDatabasePort.getSaleById(id))
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        if(saleDTO.getAmountPaid() < car.getPrice()-sale.getAmountPaid()){
            log.info("[Amount paid is not enough to complete sale]");
            throw new RuntimeException("Amount paid is not enough to complete sale");
        }

        car.setStatus("PAID");
        return saleDatabasePort.finalizeSale(updatePayment(saleDTO, sale),id);
    }

    private SaleDTO updatePayment(SaleDTO newSale, SaleDTO databaseSale) {
        log.info("[SalePortImpl.updatePayment]");
        databaseSale.setStatus("PAID");
        databaseSale.setAmountPaid(databaseSale.getAmountPaid() + newSale.getAmountPaid());
        databaseSale.setUpdatedAt(LocalDateTime.now());
        return databaseSale;
    }

    private NewSaleDTO buildNewSale(SaleDTO sale, CarDTO car) {
        return new NewSaleDTO(
                sale.getId(),
                sale.getCustomerId(),
                car,
                sale.getStatus(),
                sale.getAmountPaid(),
                sale.getCreatedAt());
    }
}
