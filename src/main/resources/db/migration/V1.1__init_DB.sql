DROP SEQUENCE IF EXISTS purchases_id_seq;
create sequence purchases_id_seq start 1 increment 1;


CREATE TABLE purchases
(
    id            BIGINT NOT NULL UNIQUE,
    purchase_date timestamp NOT NULL
);


DROP SEQUENCE IF EXISTS categories_id_seq;
create sequence categories_id_seq start 1 increment 1;

CREATE TABLE categories
(
    id            BIGINT NOT NULL UNIQUE,
    category_name varchar(50) NOT NULL
);


DROP SEQUENCE IF EXISTS manufacturers_id_seq;
create sequence manufacturers_id_seq start 1 increment 1;

CREATE TABLE manufacturers
(
    id                BIGINT NOT NULL UNIQUE,
    manufacturer_name varchar(50) NOT NULL
);


DROP SEQUENCE IF EXISTS products_id_seq;
create sequence products_id_seq start 1 increment 1;

CREATE TABLE products
(
    id              BIGINT NOT NULL UNIQUE,
    product_url        text not NULL,
    manufacturer_id BIGINT NOT NULL REFERENCES manufacturers (id),
    category_id     BIGINT NOT NULL REFERENCES categories (id),
    product_name    text   NOT NULL,
    price           int    NOT NULL CHECK (price > 0)
);


DROP SEQUENCE IF EXISTS deliveries_id_seq;
create sequence deliveries_id_seq start 1 increment 1;

CREATE TABLE deliveries
(
    id            BIGINT NOT NULL UNIQUE,
    product_id    BIGINT NOT NULL REFERENCES products (id),
    delivery_date date   NOT NULL,
    product_count int    NOT NULL
);


DROP SEQUENCE IF EXISTS purchased_items_id_seq;
create sequence purchased_items_id_seq start 1 increment 1;

CREATE TABLE purchased_items
(
    id            BIGINT NOT NULL UNIQUE,
    purchase_id   BIGINT NOT NULL REFERENCES purchases (id),
    product_id    BIGINT NOT NULL REFERENCES products (id),
    product_count int    NOT NULL CHECK (product_count > 0)
);


DROP SEQUENCE IF EXISTS price_changes_id_seq;
create sequence price_changes_id_seq start 1 increment 1;

CREATE TABLE price_changes
(
    id                BIGINT NOT NULL UNIQUE,
    product_id        BIGINT    NOT NULL REFERENCES products (id),
    price_change_date timestamp NOT NULL,
    old_price         int       NOT NULL
);

CREATE TABLE delivery_products
(
    id          BIGINT NOT NULL UNIQUE,
    delivery_id BIGINT REFERENCES deliveries (id),
    product_id  BIGINT REFERENCES products (id)
);


DROP SEQUENCE IF EXISTS user_id_seq;
create sequence users_id_seq start 1 increment 1;

CREATE TABLE users
(
    id        BIGINT NOT NULL UNIQUE,
    username  varchar(20) NOT NULL UNIQUE,
    role_name varchar(20),
    password  text        NOT NULL,
    email     varchar(20) NOT NULL,
    CONSTRAINT username_unique UNIQUE (username)
);