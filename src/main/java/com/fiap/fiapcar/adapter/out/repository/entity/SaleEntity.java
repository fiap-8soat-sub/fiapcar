package com.fiap.fiapcar.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALE")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq_gen")
    @SequenceGenerator(name = "sale_seq_gen", sequenceName = "sale_id_seq", allocationSize = 1)
    @Column(name = "SALE_ID")
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "CAR_ID", nullable = false)
    private String carId;

    @Column(name = "AMOUNT_PAID", nullable = false)
    private String amountPaid;

    @Column(name = "STATUS", nullable = false)
    private String status;
    //ENUM HERE

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
