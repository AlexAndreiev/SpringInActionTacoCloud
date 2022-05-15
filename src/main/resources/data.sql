DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Taco;
DELETE FROM Taco_Order;

DELETE FROM Ingredient;
INSERT INTO Ingredient (id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO Ingredient (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');

-- users
-- password = "12345"
INSERT INTO USER(id, city, fullname, password, phone_number, state, street, username, zip)
VALUES
    (1, 'city1', 'Alex', '$2a$10$euR.kVMKOB4vR2hIJ2zQNOHUaJVYjTTytmGt/KkxwaUIn8NmQ32EO', '0000000', 'state1', 'street1',
    	'alex',	'68532')
