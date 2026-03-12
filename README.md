# VideoGamesStore

Este repositorio contiene el análisis, diseño estructural y documentación técnica de **VideoGamesStore**, una plataforma web diseñada para la gestión y compra simulada de videojuegos. Este proyecto se desarrolla en el marco de la asignatura **Fundamentos de Ingeniería de Software**.

## Descripción del Proyecto
El sistema es una plataforma interactiva que permite a los usuarios registrarse, explorar un catálogo de productos y simular transacciones de compra. Incluye un panel de administración para la gestión de inventario, stock y promociones.

## Gestión de Datos y Persistencia
Para esta fase del proyecto, el sistema opera **completamente en memoria**. 
* **Persistencia Temporal**: Los datos se gestionan mediante estructuras de datos internas (como colecciones de Java) durante el tiempo de ejecución.
* **Simulación Lógica**: Esta arquitectura permite validar la lógica de negocio y los flujos de usuario sin depender de un motor de base de datos externo, facilitando un entorno de pruebas ágil y autocontenido.

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
