# Diagramas UML

Esta carpeta contiene el modelado visual del sistema **VideoGamesStore**, definiendo su estructura estática y comportamiento dinámico.

## Nota sobre la Arquitectura de Datos
Aunque los diagramas (Contexto, Entidad-Relación y Secuencia) incluyen componentes representados con el ícono tradicional de **Base de Datos**, en la implementación actual dicho componente representa el **Módulo de Almacenamiento en Memoria**. 
* Las interacciones de "Actualizar info" o "Consultar datos" se realizan directamente sobre las estructuras de datos cargadas en la aplicación.

## Contenido de la Carpeta
* **Diagrama de Contexto**: Define las fronteras del sistema y actores externos.
* **Diagrama de Clases**: Detalla la jerarquía de objetos, incluyendo `Usuario`, `Videojuego` y patrones de diseño para promociones.
* **Diagramas de Casos de Uso**: Flujos para registro, compra, filtrado y gestión de juegos.
* **Diagramas de Secuencia**: Representan la lógica paso a paso de procesos como el checkout y la autenticación.
