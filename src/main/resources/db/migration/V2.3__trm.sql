CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_product_name_trgm ON products USING gin (product_name gin_trgm_ops);
CREATE INDEX idx_manufacturer_name_trgm ON manufacturers USING gin (manufacturer_name gin_trgm_ops);
CREATE INDEX idx_category_name_trgm ON categories USING gin (category_name gin_trgm_ops);

