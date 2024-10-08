DELETE FROM taco_order_tacos;
DELETE FROM taco_ingredients;
DELETE FROM taco;
DELETE FROM taco_order;

DELETE FROM ingredient;
INSERT INTO ingredient (id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO ingredient (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO ingredient (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO ingredient (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO ingredient (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO ingredient (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO ingredient (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO ingredient (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO ingredient (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO ingredient (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');

-- users
-- password = "12345"
INSERT INTO user(id, city, fullname, password, phone_number, state, street, username, zip)
VALUES
    (1, 'city1', 'Alex', '$2a$10$euR.kVMKOB4vR2hIJ2zQNOHUaJVYjTTytmGt/KkxwaUIn8NmQ32EO', '0000000',
     'state1', 'street1', 'alex', '68532')
