-- Sequences com nomes customizados
CREATE SEQUENCE brand_id_seq START 1 INCREMENT 1 CACHE 50;
CREATE SEQUENCE car_id_seq   START 1 INCREMENT 1 CACHE 50;
CREATE SEQUENCE sale_id_seq  START 1 INCREMENT 1 CACHE 50;

-- =========================
-- BRAND
-- =========================
CREATE TABLE "BRAND" (
                         "BRAND_ID"   BIGINT PRIMARY KEY DEFAULT nextval('brand_id_seq'),
                         "NAME"       VARCHAR(255) NOT NULL,
                         "CREATED_AT" DATE NOT NULL,
                         "UPDATED_AT" DATE NOT NULL,
                         CONSTRAINT "UQ_BRAND_NAME" UNIQUE ("NAME")
);

-- =========================
-- CAR
-- =========================
CREATE TABLE "CAR" (
                       "CAR_ID"       BIGINT PRIMARY KEY DEFAULT nextval('car_id_seq'),
                       "BRAND_ID"     VARCHAR(255) NOT NULL,         -- mantém String conforme Entity
                       "MODEL"        VARCHAR(255) NOT NULL,
                       "YEAR"         INTEGER NOT NULL,
                       "COLOR"        VARCHAR(64) NOT NULL,
                       "CONDITION"    VARCHAR(32) NOT NULL,          -- ENUM futuramente
                       "FUEL_TYPE"    VARCHAR(32) NOT NULL,          -- ENUM futuramente
                       "TRANSMISSION" BIGINT NOT NULL,               -- mantém Long conforme Entity
                       "MILEAGE"      INTEGER NOT NULL,
                       "PRICE"        DOUBLE PRECISION NOT NULL,     -- mantém double conforme Entity
                       "STATUS"       VARCHAR(32) NOT NULL,          -- ENUM futuramente
                       "PLATE"        VARCHAR(32) NOT NULL,
                       "DESCRIPTION"  TEXT,
                       "CREATED_AT"   DATE NOT NULL,
                       "UPDATED_AT"   DATE NOT NULL,
                       CONSTRAINT "UQ_CAR_PLATE" UNIQUE ("PLATE")
);

-- Índices úteis
CREATE INDEX "IDX_CAR_STATUS" ON "CAR"("STATUS");
CREATE INDEX "IDX_CAR_PRICE"  ON "CAR"("PRICE");

-- =========================
-- SALE
-- =========================
CREATE TABLE "SALE" (
                        "SALE_ID"     BIGINT PRIMARY KEY DEFAULT nextval('sale_id_seq'),
                        "CUSTOMER_ID" VARCHAR(255) NOT NULL,          -- mantém String conforme Entity (ID externo do Auth)
                        "CAR_ID"      VARCHAR(255) NOT NULL,          -- mantém String conforme Entity
                        "AMOUNT_PAID" VARCHAR(64)  NOT NULL,          -- mantém String conforme Entity (evoluir para NUMERIC depois)
                        "STATUS"      VARCHAR(32)  NOT NULL,          -- ENUM futuramente
                        "CREATED_AT"  DATE NOT NULL,
                        "UPDATED_AT"  DATE NOT NULL
);

-- Índice opcional para buscas por status
CREATE INDEX "IDX_SALE_STATUS" ON "SALE"("STATUS");
