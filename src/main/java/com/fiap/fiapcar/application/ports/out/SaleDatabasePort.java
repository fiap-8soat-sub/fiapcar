package com.fiap.fiapcar.application.ports.out;

import com.fiap.fiapcar.application.model.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleDatabasePort {

    SaleDTO createSale(SaleDTO saleDTO);

    SaleDTO getSaleById(Long id);

    Page<SaleDTO> getAllSales(Pageable pageable);

    SaleDTO finalizeSale(SaleDTO saleDTO, Long id);

    boolean getSaleByCarId(Long carId);
}
