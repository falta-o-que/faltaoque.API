-- PURCHASES -> PANTRIES
ALTER TABLE purchases
    DROP FOREIGN KEY fk_purchases_pantry;

ALTER TABLE purchases
    ADD CONSTRAINT fk_purchases_pantry
        FOREIGN KEY (pantry_id)
            REFERENCES pantries (id)
            ON DELETE CASCADE;

-- PANTRY PRODUCTS -> PURCHASES
ALTER TABLE pantry_products
    DROP FOREIGN KEY fk_pantry_products_purchase;

ALTER TABLE pantry_products
    ADD CONSTRAINT fk_pantry_products_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchases (id)
            ON DELETE CASCADE;

-- GROCERY LISTS -> PANTRIES
ALTER TABLE grocery_lists
    DROP FOREIGN KEY fk_grocery_lists_pantry_id_pantries;

ALTER TABLE grocery_lists
    ADD CONSTRAINT fk_grocery_lists_pantry_id_pantries
        FOREIGN KEY (pantry_id)
            REFERENCES pantries (id)
            ON DELETE CASCADE;

-- GROCERY LIST PRODUCTS -> GROCERY LISTS
ALTER TABLE grocery_list_products
    DROP FOREIGN KEY fk_grocery_list_products_grocery_list_id_grocery_lists;

ALTER TABLE grocery_list_products
    ADD CONSTRAINT fk_grocery_list_products_grocery_list_id_grocery_lists
        FOREIGN KEY (grocery_list_id)
            REFERENCES grocery_lists (id)
            ON DELETE CASCADE;
