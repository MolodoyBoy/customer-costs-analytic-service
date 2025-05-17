CREATE TABLE customer_costs(
    id SERIAL PRIMARY KEY,
    amount NUMERIC NOT NULL,
    user_id INTEGER NOT NULL,
    description VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    category_id INTEGER NOT NULL REFERENCES customer_costs_category(id)
);