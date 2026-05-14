# TechZone Gaming - Backend API ⚙️

Este módulo contiene la API REST que sirve como núcleo lógico para el sistema VideoGamesStore. Está construida bajo una arquitectura en capas (Controladores, Servicios, Repositorios) utilizando **Java y Spring Boot**.

## 🛠️ Tecnologías y Dependencias
* **Java 17+**
* **Spring Boot** (Web, Data JPA, Security)
* **JWT (JSON Web Tokens)** para autenticación y autorización stateless.
* **Swagger / OpenAPI** para documentación interactiva.
* **Maven** para la gestión de dependencias.

## 🗂️ Estructura Principal
* `config/`: Configuraciones CORS y Swagger.
* `controllers/`: Endpoints de la API (Auth, Videojuego, Pedido, etc.).
* `dto/`: Objetos de transferencia de datos (Requests/Responses).
* `entities/`: Modelado del dominio (JPA Entities).
* `repositories/`: Interfaces de Spring Data JPA.
* `security/`: Filtros y utilidades JWT (`JwtFilter`, `JwtUtil`).
* `services/`: Lógica de negocio transaccional.

## 🚀 Instalación y Ejecución

1. **Configurar la Base de Datos:**
   Asegúrate de tener un gestor de base de datos (PostgreSQL/MySQL) corriendo. Configura tus credenciales y URL en `src/main/resources/application.properties`.
   *(Nota: Los scripts `schema.sql` y `data.sql` inicializarán la estructura y datos por defecto).*

2. **Compilar e Instalar dependencias:**
   ```bash
   mvn clean install
   mvn spring-boot:run
