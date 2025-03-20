CREATE TABLE coffee_machine (
    id BIGSERIAL PRIMARY KEY
);

CREATE TABLE ingredients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity BIGINT NOT NULL CHECK (quantity >= 0 AND quantity <= 1000)
);

CREATE TABLE recipe (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE recipe_ingredients (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id),
    ingredients_id BIGINT NOT NULL REFERENCES ingredients(id),
    amount BIGINT NOT NULL
);

CREATE TABLE coffee_machine_recipe (
    id BIGSERIAL PRIMARY KEY,
    coffee_machine_id BIGINT NOT NULL REFERENCES coffee_machine(id),
    recipe_id BIGINT NOT NULL REFERENCES recipe(id)
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id),
    date TIMESTAMP NOT NULL
);

INSERT INTO recipe (name)
VALUES ('Espresso'),
       ('Americano'),
       ('Cappuccino');

INSERT INTO ingredients (name, quantity)
VALUES ('water', 1000),
       ('coffee powder', 1000),
       ('milk', 1000),
       ('chocolate', 1000);

INSERT INTO recipe_ingredients (recipe_id, ingredients_id, amount)
VALUES
        (1, 1, 30),
        (1,2, 10),

        (2, 1, 100),
        (2, 2, 10),

        (3, 1, 50),
        (3, 2, 10),
        (3, 3, 50);

