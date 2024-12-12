BASES

cliente_persona_db


CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    persona_id INT NOT NULL,
    cliente_id VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(200) NOT NULL,
    estado BOOLEAN NOT NULL,
    CONSTRAINT fk_persona FOREIGN KEY (persona_id) REFERENCES persona (id)
);


cuenta_movimientos_db



CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial NUMERIC(10, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id INT NOT NULL
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