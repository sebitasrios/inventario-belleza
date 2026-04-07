Sistema de Inventario para Tienda de Belleza 💄✨
Proyecto desarrollado como parte del curso de Programación de Software en el Instituto Tecnológico Metropolitano. Consiste en una API REST robusta para la gestión de inventarios, implementada con Java y Spring Boot.

📋 Tabla de Contenidos
Descripción
Objetivos
Tecnologías Utilizadas
Arquitectura del Proyecto
Base de Datos
Instalación y Ejecución
Documentación de la API (Swagger)
Endpoints Disponibles
📖 Descripción
Sistema de gestión de inventario que permite controlar de manera eficiente el registro, consulta, actualización y eliminación de productos de belleza. La aplicación facilita la organización del negocio y el control del stock disponible a través de una arquitectura limpia y servicios RESTful.

🎯 Objetivos
Registrar productos: Ingreso de nombre, categoría, precio y cantidad.
Consultar inventario: Visualización general o búsqueda por ID.
Actualizar datos: Modificación de precios, cantidades o categorías.
Eliminar productos: Limpieza del inventario de artículos no disponibles.
Control de stock: Identificación de productos disponibles o agotados.
🛠️ Tecnologías Utilizadas
Java 17
Spring Boot (Framework principal)
SQL Server (Base de datos)
JDBC (Conexión a base de datos manual)
Maven (Gestión de dependencias)
Swagger / Springdoc OpenAPI (Documentación)
Git & GitHub (Control de versiones)
🏗️ Arquitectura del Proyecto
El proyecto sigue una arquitectura por capas para separar responsabilidades:

com.belleza.inventario├── controllers    # Manejo de peticiones HTTP y endpoints.├── services       # Lógica de negocio.├── dao            # Acceso a datos (Implementación JDBC manual).├── entities       # Entidades del sistema (Modelos).└── util           # Clases de utilidad (Conexión DB).
💾 Base de Datos
Se utiliza SQL Server. A continuación, el script para crear la base de datos y la tabla necesaria:

sql

CREATE DATABASE inventario_belleza;
USE inventario_belleza;

CREATE TABLE producto (
id INT IDENTITY(1,1) PRIMARY KEY,
nombre VARCHAR(100),
categoria VARCHAR(50),
precio FLOAT,
cantidad INT
);
🚀 Instalación y Ejecución
Requisitos Previos
Tener instalado Java JDK 17.
Tener SQL Server instalado y corriendo.
Maven (o usar el wrapper incluido en el proyecto).
Configuración
Clona el repositorio:
bash

git clone https://github.com/tu-usuario/tu-repositorio.git
Abre el archivo src/main/resources/application.properties.
Asegúrate de que la conexión coincida con tu configuración local. El proyecto usa autenticación de Windows por defecto:
properties

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=inventario_belleza;encrypt=false;integratedSecurity=true
Ejecución
Puedes ejecutar el proyecto usando Maven en la terminal:

bash

./mvnw spring-boot:run
O bien, abriendo el proyecto en tu IDE (IntelliJ, Eclipse, VS Code) y ejecutando la clase principal.

📄 Documentación de la API (Swagger)
Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de Swagger en la siguiente URL:

http://localhost:8080/api/swagger-ui/index.html

Desde ahí podrás ver los detalles de cada endpoint y probarlos directamente.

🔗 Endpoints Disponibles
Método HTTP
URL
Descripción
GET	/api/productos	Listar todos los productos
GET	/api/productos/{id}	Buscar producto por ID
POST	/api/productos	Crear un nuevo producto
PUT	/api/productos/{id}	Actualizar un producto
DELETE	/api/productos/{id}	Eliminar un producto

Desarrollado por Sebastian Rios Rios - 2026.