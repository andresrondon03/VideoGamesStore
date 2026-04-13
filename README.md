# VideoGamesStore

Este repositorio contiene el análisis, diseño estructural y documentación técnica de **VideoGamesStore**, una plataforma web diseñada para la gestión y compra simulada de videojuegos. Este proyecto se desarrolla en el marco de la asignatura **Fundamentos de Ingeniería de Software**.

## Descripción del Proyecto
El sistema es una plataforma interactiva que permite a los usuarios registrarse, explorar un catálogo de productos y simular transacciones de compra. Incluye un panel de administración para la gestión de inventario, stock y promociones.

## Gestión de Datos y Persistencia
El sistema utiliza una Base de Datos Relacional para garantizar la persistencia de la información. 
* **Persistencia de Datos**: Toda la información de usuarios, productos, pedidos y promociones se almacena de forma estructurada, permitiendo la recuperación de datos tras el reinicio de la aplicación.
* **Integridad y Seguridad**: La implementación sigue el diseño lógico definido en el diagrama entidad-relación, asegurando la consistencia de los datos y el manejo seguro de credenciales mediante hashing.

## Características Principales
* **Catálogo Interactivo**: Búsqueda por nombre y filtrado por precio, género o plataforma.
* **Gestión de Usuarios**: Autenticación con roles de Cliente y Administrador.
* **Administración**: Herramientas para agregar, editar y eliminar productos del catálogo.

## Equipo de Desarrollo
* **Andres Felipe Rondón Lara**: Analista y Gestor de Proyecto.
* **Santiago Andrés Benavides Coral**: Desarrollador Backend y Arquitecto de Datos.
* **Davidson Sanchez Gordillo**: Desarrollador Frontend y Aseguramiento de Calidad (QA).

## Estructura del Proyecto
* `/Diagramas UML`: Modelado visual de la arquitectura.
* `/Documentación`: Informes técnicos y requerimientos.
* `/Prototipo`: Diseños de interfaz de usuario.
