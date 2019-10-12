INSERT INTO COUNTRIES
  (COUNTRY_ID, COUNTRY_NAME)
VALUES
  (1, 'Republica Dominicana'),
  (2, 'Puerto Rico'),
  (3, 'Haiti');


INSERT INTO PROVINCES
  (PROVINCE_ID, PROVINCE_NAME, COUNTRY_ID)
VALUES
  (1, 'Azua', 1),
  (2, 'Bahoruco', 1),
  (3, 'Barahona', 1),
  (4, 'Dajabón', 1),
  (5, 'Distrito Nacional', 1),
  (6, 'Duarte', 1),
  (7, 'Elías Piña', 1),
  (8, 'El Seibo', 1),
  (9, 'Espaillat', 1),
  (10, 'Hato Mayor', 1),
  (11, 'Hermanas Mirabal', 1),
  (12, 'La Altagracia', 1),
  (13, 'La Romana', 1),
  (14, 'La Vega', 1),
  (15, 'María Trinidad Sánchez', 1),
  (16, 'Monseñor Nouel', 1),
  (17, 'Monte Cristi', 1),
  (18, 'Monte Plata', 1),
  (19, 'Pedernales', 1),
  (20, 'Peravia', 1),
  (21, 'Puerto Plata', 1),
  (22, 'Samaná', 1),
  (23, 'San Cristóbal', 1),
  (24, 'San José de Ocoa', 1),
  (25, 'San Juan', 1),
  (26, 'San Pedro de Macorís', 1),
  (27, 'Sánchez Ramírez', 1),
  (28, 'Santiago Rodríguez', 1),
  (29, 'Santo Domingo', 1),
  (30, 'Valverde', 1),

  (31, 'San Juan', 2),
  (32, 'Puerto Principe', 3);

INSERT INTO CATEGORIES
  (CATEGORY_ID, CATEGORY_NAME)
VALUES
  (1, 'Comidas'),
  (2, 'Bebidas');


INSERT INTO PROVIDERS
  (PROVIDER_ID, PROVIDER_NAME, RNC_SOCIALID, EMAIL)
VALUES
  ('4ffd4dee0f704de6a5f1aae97baa5580', 'De todo barato', '402-1339312-1','barato@barato.com');

INSERT INTO PRODUCTS
  (PRODUCT_ID, PRODUCT_BARCODE, PRODUCT_NAME, BRAND, PURCHASE_PRICE, SALE_PRICE, STOCKS, ITBIS, DESCRIPTION, CATEGORY_ID, PROVIDER_ID)
VALUES
  ('b1bc03dd4367487f8f40c97fe1c00000', '07350053850019', 'Coca Cola (1/2) Litro', 'Coca cola', 15.00, 21.20, 5, 18.0, 'Coca cola pequena', 2, '4ffd4dee0f704de6a5f1aae97baa5580'),
  ('e674e5ea25534a76a1fefe54afa00001', '07350053050120', 'Oreo Pequena', 'Oreo', 8.00, 13.00, 20, 18.0, 'Oreo pequena chocolate' , 1, '4ffd4dee0f704de6a5f1aae97baa5580'),
  ('e674e5ea25534a76a1fefe54afa00002', '07350003050122', 'Bizcocho', 'Dona maria', 20, 25, 12, 0, 'Bizcochos de chocolate', 1, '4ffd4dee0f704de6a5f1aae97baa5580');
