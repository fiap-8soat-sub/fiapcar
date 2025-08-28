-- =========================
-- SEQUENCES
-- =========================
CREATE SEQUENCE brand_id_seq START WITH 1 INCREMENT BY 1 CACHE 50;
CREATE SEQUENCE car_id_seq   START WITH 1 INCREMENT BY 1 CACHE 50;
CREATE SEQUENCE sale_id_seq  START WITH 1 INCREMENT BY 1 CACHE 50;

-- =========================
-- BRAND
-- =========================
CREATE TABLE brand (
                       brand_id   BIGINT DEFAULT NEXT VALUE FOR brand_id_seq,
                       name       VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT pk_brand PRIMARY KEY (brand_id),
                       CONSTRAINT uq_brand_name UNIQUE (name)
);

-- =========================
-- CAR
-- =========================
CREATE TABLE car (
                     car_id       BIGINT DEFAULT NEXT VALUE FOR car_id_seq,
                     brand_id     BIGINT NOT NULL,               -- FK -> brand
                     model        VARCHAR(255) NOT NULL,
                     model_year   INT NOT NULL,                  -- "year" é reservado no H2
                     color        VARCHAR(64) NOT NULL,
                     condition    VARCHAR(32) NOT NULL,
                     fuel_type    VARCHAR(32) NOT NULL,
                     transmission BIGINT NOT NULL,
                     mileage      INT NOT NULL,
                     price        DOUBLE NOT NULL,
                     status       VARCHAR(32) NOT NULL,
                     plate        VARCHAR(32) NOT NULL,
                     description  TEXT,
                     created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     CONSTRAINT pk_car PRIMARY KEY (car_id),
                     CONSTRAINT uq_car_plate UNIQUE (plate),
                     CONSTRAINT fk_car_brand FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
                         ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE INDEX idx_car_status   ON car(status);
CREATE INDEX idx_car_price    ON car(price);
CREATE INDEX idx_car_brand_id ON car(brand_id);

-- =========================
-- SALE
-- =========================
CREATE TABLE sale (
                      sale_id     BIGINT DEFAULT NEXT VALUE FOR sale_id_seq,
                      customer_id UUID NOT NULL,                  -- agora UUID
                      car_id      BIGINT NOT NULL,                -- agora LONG, FK -> car
                      amount_paid VARCHAR(64) NOT NULL,           -- mantém String por enquanto
                      status      VARCHAR(32) NOT NULL,
                      created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT pk_sale PRIMARY KEY (sale_id),
                      CONSTRAINT fk_sale_car FOREIGN KEY (car_id) REFERENCES car(car_id)
                          ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE INDEX idx_sale_status   ON sale(status);
CREATE INDEX idx_sale_customer ON sale(customer_id);
CREATE INDEX idx_sale_car      ON sale(car_id);
