CREATE TABLE items
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
    price    DOUBLE PRECISION NOT NULL
);

CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    total      DOUBLE PRECISION NOT NULL
);

CREATE TABLE order_items
(
    id       SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders (id),
    item_id  INTEGER REFERENCES items (id),
    quantity   INTEGER NOT NULL
);