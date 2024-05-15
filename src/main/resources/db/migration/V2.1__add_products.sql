INSERT INTO manufacturers (id, manufacturer_name)
VALUES (1, 'Gucci');

ALTER SEQUENCE manufacturers_id_seq RESTART WITH 2;


INSERT INTO categories (id, category_name)
VALUES (1, 'Футболка');

ALTER SEQUENCE categories_id_seq RESTART WITH 2;


INSERT INTO products (id, product_url, manufacturer_id, category_id, product_name, price)
VALUES (1, 'https://cdn.lookastic
.ru/%D0%BA%D0%BE%D1%80%D0%B8%D1%87%D0%BD%D0%B5%D0%B2%D0%B0%D1%8F-%D1%84%D1%83
%D1%82%D0%B1%D0%BE%D0%BB%D0%BA%D0%B0-%D0%BF%D0%BE%D0%BB%D0%BE-%D1%81-%D0%BF
%D1%80%D0%B8%D0%BD%D1%82%D0%BE%D0%BC/gucci-original-10949791.jpg', 1, 1,
'Футболка Gucci', 15000);


ALTER SEQUENCE products_id_seq RESTART WITH 2;

