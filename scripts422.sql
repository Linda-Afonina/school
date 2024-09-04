CREATE TABLE cars (
	id INTEGER PRIMARY KEY,
	brand VARCHAR,
	model VARCHAR,
	price NUMERIC
	);
CREATE TABLE people (
	id INTEGER PRIMARY KEY,
	name VARCHAR,
	age INTEGER,
	haveCarRights BOOLEAN,
	car_id INTEGER REFERENCES cars (id)
	);