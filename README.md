<h1 align="center">Reto Técnico BP</h1>


<h2 align="center">Arquitectura  </h2>


wweb_service 1: cliente_persona

Java version: 21
DB: Postgresql:  cliente_persona_db
Broker message: Rabbit -> cloudAMQP
Estructura del proyecto: Arquitectura hexagonal orientada a modulos y puertos

wweb_service 2:


Java version: 21
DB: Postgresql: cuenta_movimientos_db
Broker message: Rabbit -> cloudAMQP
Estructura del proyecto: Arquitectura hexagonal orientada a modulos y puertos



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



