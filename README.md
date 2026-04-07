# 💄 Inventario Belleza API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=for-the-badge&logo=springboot)
![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![SQL Server](https://img.shields.io/badge/SQL%20Server-2019-blue?style=for-the-badge&logo=microsoftsqlserver)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-85EA2D?style=for-the-badge&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)

API REST para la gestión de inventario de productos de belleza.
Desarrollada con Spring Boot 3, documentada con Swagger UI y conectada a SQL Server.

---

## 📋 Tabla de contenido

- [Descripción](#-descripción)
- [Tecnologías](#-tecnologías)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Base de datos](#-base-de-datos)
- [Configuración](#-configuración)
- [Endpoints disponibles](#-endpoints-disponibles)
- [Documentación Swagger](#-documentación-swagger)
- [Cómo ejecutar](#-cómo-ejecutar)
- [Autor](#-autor)

---

## 📌 Descripción

**Inventario Belleza** es una API REST construida con Spring Boot que permite gestionar
el inventario de productos de belleza. Soporta operaciones CRUD completas y expone
su documentación interactiva mediante Swagger UI.

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.2.0 | Framework backend |
| Spring Web | - | Construcción de la API REST |
| SQL Server | - | Base de datos relacional |
| SpringDoc OpenAPI | 2.3.0 | Documentación Swagger UI |
| Maven | - | Gestión de dependencias y build |

---

## 📁 Estructura del proyecto

El proyecto respeta la arquitectura por capas:

```
src/main/java/com/belleza/inventario/
    controllers/        Endpoints REST (GET, POST, PUT, DELETE)
    entities/           Entidades que representan las tablas de la BD
    services/           Lógica de negocio
    InventarioApplication.java    Clase principal de Spring Boot
src/main/resources/
    application.properties        Configuración del servidor y BD
inventario_belleza.sql            Script de creación de la base de datos
pom.xml                           Dependencias del proyecto
README.md                         Documentacion general del proyecto
```

---

## 🗄️ Base de datos

Ejecuta este script en SQL Server Management Studio (SSMS):

```sql
CREATE DATABASE inventario_belleza;

USE inventario_belleza;

CREATE TABLE producto (
    id        INT IDENTITY(1,1) PRIMARY KEY,
    nombre    VARCHAR(100),
    categoria VARCHAR(50),
    precio    FLOAT,
    cantidad  INT
);
```

> El archivo también está disponible en la raíz del proyecto como `inventario_belleza.sql`

---

## ⚙️ Configuración

`src/main/resources/application.properties`:

```properties
spring.application.name=inventario
server.port=8080
server.servlet.context-path=/api

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=inventario_belleza;encrypt=false;integratedSecurity=true
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 🚀 Endpoints disponibles

Base URL: `http://localhost:8080/api`

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/productos` | Listar todos los productos |
| `GET` | `/productos/{id}` | Buscar producto por ID |
| `POST` | `/productos` | Crear un nuevo producto |
| `PUT` | `/productos/{id}` | Actualizar un producto existente |
| `DELETE` | `/productos/{id}` | Eliminar un producto por ID |

### Ejemplo Body (POST / PUT)

```json
{
  "nombre": "Shampoo Keratina",
  "categoria": "Cabello",
  "precio": 35000,
  "cantidad": 50
}
```

---

## 📄 Documentación Swagger

Con la app corriendo, accede en:
http://localhost:8080/api/swagger-ui.html

---

## ▶️ Cómo ejecutar

**Prerrequisitos:** Java 17, Maven, SQL Server activo con `inventario_belleza` creada.

```bash
# Clonar
git clone https://github.com/sebitasrios/inventario-belleza.git
cd inventario-belleza

# Ejecutar
./mvnw spring-boot:run
```

O desde IntelliJ: ejecuta `InventarioApplication.java` con ▶️ Run.

---

## 👤 Autor

**Sebastian Rios** — [github.com/sebitasrios](https://github.com/sebitasrios)

---

> Desarrollado  usando Spring Boot ·  2026