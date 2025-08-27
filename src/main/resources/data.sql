INSERT INTO brand (brand_id, name, created_at, updated_at) VALUES
                                                               (1,'Volkswagen', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (2,'Chevrolet',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (3,'Fiat',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (4,'Toyota',     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (5,'Honda',      CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (6,'Ford',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (7,'Hyundai',    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (8,'Renault',    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (9,'Peugeot',    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                               (10,'Jeep',      CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

ALTER SEQUENCE brand_id_seq RESTART WITH 11;

INSERT INTO car (
    car_id, brand_id, model, model_year, color, condition, fuel_type,
    transmission, mileage, price, status, plate, description, created_at, updated_at
) VALUES
      (1001,'VW','Tiguan Allspace R-Line',2021,'branco','USED','FLEX',2,42000, 209900.00,'AVAILABLE','RDO1A23','Top de linha, teto panorâmico',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1002,'CHEV','Onix Plus LTZ',        2022,'prata','USED','FLEX',2,18000,  88990.00,'AVAILABLE','ABC2B34','Câmera de ré e MyLink',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1003,'FIAT','Argo Trekking',        2021,'vermelho','USED','FLEX',1,25000, 74990.00,'AVAILABLE','DEF3C45','Suspensão elevada',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1004,'TOY','Corolla Altis',         2019,'preto','USED','GASOLINA',2,52000,129900.00,'AVAILABLE','GHI4D56','Banco em couro',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1005,'HONDA','Civic Touring',       2020,'cinza','USED','GASOLINA',2,40000,139900.00,'AVAILABLE','JKL5E67','Turbo 1.5',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1006,'FORD','Ranger XLT',           2018,'azul','USED','DIESEL',2,78000,159900.00,'AVAILABLE','MNO6F78','4x4 cabine dupla',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1007,'HYUNDAI','HB20 Comfort',      2021,'branco','USED','FLEX',1,22000, 67990.00,'AVAILABLE','PQR7G89','Baixa km',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1008,'RENAULT','Duster Intense',    2020,'prata','USED','FLEX',2,35000, 84990.00,'AVAILABLE','STU8H01','Espaçoso',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1009,'PEUGEOT','208 Griffe',        2022,'azul','USED','FLEX',2,12000, 94990.00,'AVAILABLE','VWX9I12','i-Cockpit',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (1010,'JEEP','Compass Limited',      2021,'preto','USED','FLEX',2,30000,164900.00,'AVAILABLE','YZA0J23','AD 1.3 T270',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

ALTER SEQUENCE car_id_seq RESTART WITH 1011;

INSERT INTO sale (
    sale_id, customer_id, car_id, amount_paid, status, created_at, updated_at
) VALUES
      (2001,'auth0|user_001','1001','209900.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2002,'auth0|user_002','1002',' 88990.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2003,'auth0|user_003','1003',' 74990.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2004,'auth0|user_004','1004','129900.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2005,'auth0|user_005','1005','139900.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2006,'auth0|user_006','1006','159900.00','PENDING_PAYMENT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2007,'auth0|user_007','1007',' 67990.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2008,'auth0|user_008','1008',' 84990.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2009,'auth0|user_009','1009',' 94990.00','CANCELED',        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      (2010,'auth0|user_010','1010','164900.00','PAID',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

ALTER SEQUENCE sale_id_seq RESTART WITH 2011;
