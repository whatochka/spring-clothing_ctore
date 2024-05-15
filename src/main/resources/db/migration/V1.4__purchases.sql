ALTER TABLE purchases DROP COLUMN purchase_date;

ALTER TABLE purchases RENAME TO buckets;

ALTER TABLE purchased_items DROP COLUMN product_count;

ALTER TABLE purchased_items RENAME COLUMN purchase_id TO bucket_id;

ALTER TABLE buckets
    ADD COLUMN user_id BIGINT;

ALTER TABLE buckets
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);