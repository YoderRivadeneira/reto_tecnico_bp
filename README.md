<h1 align="center">Reto Técnico BP</h1>


<h2 align="center">Arquitectura  </h2>


<h4>web_service 1: cliente_persona</h4>

Java version: 21

DB: Postgresql:  cliente_persona_db

Broker message: Rabbit -> cloudAMQP


Estructura del proyecto: Arquitectura hexagonal orientada a modulos y puertos, clean code, arquitectura limpia


<h4>web_service 2: cuenta_movimientos</h4>


Java version: 21

DB: Postgresql: cuenta_movimientos_db

Broker message: Rabbit -> cloudAMQP

Estructura del proyecto: Estructura convencional de  arquitectura limpia y clean code


<h2 align="center">Comunicación asincrona  entre los web services-> orientada a eventos </h2>


Cuando se crea un nuevo cliente en el web service 1, este envía el evento a cloudAMQP, el web service 2 recibe este evento y le crea una cuenta



Cuando se modifica el estado de un  cliente de "true" a "false" en el web service 1, este envía el evento a cloudAMQP, el web service 2 recibe este evento y si el estado del cliente es false actualiza el saldo de todas las  cuentas de ese cliente a 0.



<h2 align="center">F1: Generación de CRUDS (Crear, editar, actualizar y eliminar registros - Entidades: Cliente, 
Cuenta y Movimiento). </h2>


Los nombres de los endpoints a generar son: 
• /cuentas 
• /clientes 
• /movimientos 


Completo = 100%, se adjunta la colección postman con los datos de envío y las respuestas.

<h2 align="center">F2 y F3: Registro de movimientos </h2>


• Para un movimiento se pueden tener valores positivos o negativos. 
• Al realizar un movimiento se debe actualizar el saldo disponible.  
• Se debe llevar el registro de las transacciones realizadas 
• Defina, según su expertise, la mejor manera de capturar y mostrar el error. 

Completo = 100%, se adjunta la colección postman con los datos de envío y las respuestas.

<h2 align="center">F4:  Reportes </h2>

Completo = 100%, se adjunta la colección postman con los datos de envío y las respuestas.

<h2 align="center">F5 y F6: Pruebas unitarias y pruebas de integración</h2>



Completo = 100%

![image](https://github.com/user-attachments/assets/52d4a8d4-f332-4d1d-b627-991c0edee4cd)

![image](https://github.com/user-attachments/assets/6dc528e2-6395-4070-b838-67280003f623)

![image](https://github.com/user-attachments/assets/b7ad1e92-0047-48bc-9d85-8fd083b67aec)


PRUEBAS UNITARIAS



![image](https://github.com/user-attachments/assets/b265f8de-757c-44ee-a162-a24d2d25b370)



PRUEBAS DE INTEGRACION


![image](https://github.com/user-attachments/assets/c3e62dee-864a-4be1-a46e-369567bfe265)







<h2 align="center">F7: Despliegue de la solución en contenedores </h2>

Se creó los DockerFile para el respectivo despliegue en contenedores docker

Web_service 1:


  - Modulo principal
    
![image](https://github.com/user-attachments/assets/76ebef5d-e598-4c45-ad13-4b470123a727)


  - Modulo de infraestructura = postgresql_repository


![image](https://github.com/user-attachments/assets/f6afac5d-ba9a-4284-9515-243ef41d83b2)


  - Modulo de infraestructura = rabbit_bus


![image](https://github.com/user-attachments/assets/a980dd31-9de8-4ed4-96f5-3746d83317e6)



Web_service 2:


  - Modulo principal

    
![image](https://github.com/user-attachments/assets/fa210b2c-9f3d-4893-911b-46849f257a87)









<h2 align="center">Documentación </h2>


<h4>SWAGGER</h4>


![image](https://github.com/user-attachments/assets/3d47d89d-e40b-4830-8988-e1ce034631c5)




<h4 >Broker message</h4>


![image](https://github.com/user-attachments/assets/03d6de97-1855-4446-86f2-aad0a04b31be)


![image](https://github.com/user-attachments/assets/ea0ef2c2-0ed9-41ca-bdc6-5954822c7055)


![image](https://github.com/user-attachments/assets/b0d1ab94-5bc0-4d3a-bf9b-85f98be13a5e)



<h4>DB</h4>


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

