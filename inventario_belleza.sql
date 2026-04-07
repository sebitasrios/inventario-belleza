CREATE DATABASE inventario_belleza;

USE inventario_belleza;

CREATE TABLE producto (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(50),
    precio FLOAT,
    cantidad INT
);