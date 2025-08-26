package com.fiap.fiapcar.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand", schema = "public")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq_gen")
    @SequenceGenerator(name = "brand_seq_gen", sequenceName = "brand_id_seq", allocationSize = 1)
    @Column(name = "brand_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

}
