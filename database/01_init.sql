-- =========================
-- Sequences com nomes customizados
-- =========================
CREATE SEQUENCE brand_id_seq START 1 INCREMENT 1 CACHE 50;
CREATE SEQUENCE car_id_seq   START 1 INCREMENT 1 CACHE 50;
CREATE SEQUENCE sale_id_seq  START 1 INCREMENT 1 CACHE 50;

-- =========================
-- BRAND
-- =========================
CREATE TABLE brand (
                       brand_id   BIGINT PRIMARY KEY DEFAULT nextval('brand_id_seq'),
                       name       VARCHAR(255) NOT NULL,
                       created_at DATE NOT NULL,
                       updated_at DATE NOT NULL,
                       CONSTRAINT uq_brand_name UNIQUE (name)
);

-- =========================
-- CAR
-- =========================
CREATE TABLE car (
                     car_id       BIGINT PRIMARY KEY DEFAULT nextval('car_id_seq'),
                     brand_id     VARCHAR(255) NOT NULL,     -- mantém String conforme Entity
                     model        VARCHAR(255) NOT NULL,
                     year         INTEGER NOT NULL,
                     color        VARCHAR(64) NOT NULL,
                     condition    VARCHAR(32) NOT NULL,      -- ENUM futuramente
                     fuel_type    VARCHAR(32) NOT NULL,      -- ENUM futuramente
                     transmission BIGINT NOT NULL,           -- mantém Long conforme Entity
                     mileage      INTEGER NOT NULL,
                     price        DOUBLE PRECISION NOT NULL, -- mantém double conforme Entity
                     status       VARCHAR(32) NOT NULL,      -- ENUM futuramente
                     plate        VARCHAR(32) NOT NULL,
                     description  TEXT,
                     created_at   DATE NOT NULL,
                     updated_at   DATE NOT NULL,
                     CONSTRAINT uq_car_plate UNIQUE (plate)
);

-- Índices úteis
CREATE INDEX idx_car_status ON car(status);
CREATE INDEX idx_car_price  ON car(price);

-- =========================
-- SALE
-- =========================
CREATE TABLE sale (
                      sale_id     BIGINT PRIMARY KEY DEFAULT nextval('sale_id_seq'),
                      customer_id VARCHAR(255) NOT NULL,      -- mantém String conforme Entity (ID externo do Auth)
                      car_id      VARCHAR(255) NOT NULL,      -- mantém String conforme Entity
                      amount_paid VARCHAR(64)  NOT NULL,      -- mantém String conforme Entity (evoluir para NUMERIC depois)
                      status      VARCHAR(32)  NOT NULL,      -- ENUM futuramente
                      created_at  DATE NOT NULL,
                      updated_at  DATE NOT NULL
);

-- Índice opcional para buscas por status
CREATE INDEX idx_sale_status ON sale(status);
