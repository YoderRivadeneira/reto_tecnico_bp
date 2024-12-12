
DO
$$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'cliente_persona_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE cliente_persona_db');
   END IF;
END
$$;


\c cliente_persona_db;

CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);


INSERT INTO persona (id, nombre, genero, edad, identificacion, direccion, telefono) VALUES
(1, 'Juan is updt y send rabbit', 'M', 31, '12345678', 'Calle Verdadera 456', '987654321'),
(2, 'Yoder', 'M', 30, '6777886699', 'Calle Verdadera 456', '987654321'),
(3, 'Yoder3', 'M', 30, '6777886698', 'Calle Verdadera 456', '987654321'),
(4, 'Juan4', 'M', 31, '12345621', 'Calle Verdadera 456', '987654351'),
(5, 'updatewe rabbbit', 'M', 31, '12345666678', 'Calle Verdadera 456', '987654321');


CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    persona_id INT NOT NULL,
    cliente_id VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(200) NOT NULL,
    estado BOOLEAN NOT NULL,
    CONSTRAINT fk_persona FOREIGN KEY (persona_id) REFERENCES persona (id)
);


\c cuenta_movimientos_db;


DO
$$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'cuenta_movimientos_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE cuenta_movimientos_db');
   END IF;
END
$$;


CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL DEFAULT 'Ahorro',
    saldo_inicial NUMERIC(10, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id INT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);


CREATE TABLE movimiento (
    id SERIAL PRIMARY KEY,
    cuenta_id INT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    saldo NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);


ALTER TABLE cuenta
ALTER COLUMN cliente_id TYPE INTEGER USING cliente_id::INTEGER;


ALTER TABLE cuenta
ALTER COLUMN tipo_cuenta SET DEFAULT 'Ahorro';
