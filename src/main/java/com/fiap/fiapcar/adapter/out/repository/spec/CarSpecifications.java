package com.fiap.fiapcar.adapter.out.repository.spec;

import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class CarSpecifications {
    private CarSpecifications() {}

    public static Specification<CarEntity> hasStatus(String status) {
        return (root, q, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<CarEntity> hasBrandId(Long brandId) {
        return (root, q, cb) -> brandId == null ? null : cb.equal(root.get("brandId"), brandId);
    }

    public static Specification<CarEntity> hasModelYear(Integer year) {
        return (root, q, cb) -> year == null ? null : cb.equal(root.get("modelYear"), year);
    }

    public static Specification<CarEntity> priceGte(BigDecimal min) {
        return (root, q, cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<CarEntity> priceLte(BigDecimal max) {
        return (root, q, cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("price"), max);
    }

    public static Specification<CarEntity> modelLike(String model) {
        return (root, q, cb) -> model == null ? null : cb.like(cb.lower(root.get("model")), "%" + model.toLowerCase() + "%");
    }
}

