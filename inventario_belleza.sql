CREATE DATABASE inventario_belleza;
go

USE inventario_belleza;
go

--creacion de la tabla categoria
CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL
);

--creacion de la tabla proveedor
CREATE TABLE proveedor (
    id_proveedor INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100)
);

--creacion de la tabla producto
CREATE TABLE producto (
    id_producto INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    stock_minimo INT DEFAULT 5,
    id_categoria INT,
    id_proveedor INT,
    
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),

    CONSTRAINT fk_producto_proveedor
        FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

--creacion de la tabla usuario
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(100) NOT NULL
);

--creacion de la tabla entrada
CREATE TABLE entrada (
    id_entrada INT PRIMARY KEY IDENTITY(1,1),
    fecha DATE NOT NULL DEFAULT GETDATE(),
    id_proveedor INT,

    CONSTRAINT fk_entrada_proveedor
        FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

--creacion de la tabla salida
CREATE TABLE salida (
    id_salida INT PRIMARY KEY IDENTITY(1,1),
    fecha DATE NOT NULL DEFAULT GETDATE()
);

--creacion de la tabla detalle_movimiento
CREATE TABLE detalle_movimiento (
    id_detalle INT PRIMARY KEY IDENTITY(1,1),
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    tipo_movimiento VARCHAR(10) NOT NULL, -- 'entrada' o 'salida'
    id_entrada INT NULL,
    id_salida INT NULL,

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (id_producto) REFERENCES producto(id_producto),

    CONSTRAINT fk_detalle_entrada
        FOREIGN KEY (id_entrada) REFERENCES entrada(id_entrada),

    CONSTRAINT fk_detalle_salida
        FOREIGN KEY (id_salida) REFERENCES salida(id_salida)
);

--creacion de la restriccion para el tipo_movimiento
ALTER TABLE detalle_movimiento
ADD CONSTRAINT chk_tipo_movimiento
CHECK (tipo_movimiento IN ('entrada', 'salida'));

-- consultas de ver inventario
SELECT nombre, stock FROM producto;

-- consulta para ver productos con stock bajo
SELECT nombre, stock 
FROM producto 
WHERE stock < stock_minimo;

--consulta para ver movimientos de entrada y salida
SELECT p.nombre, d.cantidad, d.tipo_movimiento
FROM detalle_movimiento d
JOIN producto p ON d.id_producto = p.id_producto;
