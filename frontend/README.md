# TechZone Gaming - Frontend 🌐

Este módulo contiene la interfaz de usuario web del sistema. Ha sido diseñada con un enfoque modular y responsivo, permitiendo a los clientes explorar el catálogo y a los administradores gestionar el inventario a través del Panel de Control.

## 🛠️ Tecnologías
* **Astro**: Framework web para una renderización ultra rápida.
* **TypeScript**: Para el tipado estricto y seguridad en el código (`/lib/types.ts`).
* **Tailwind CSS**: Para el diseño ágil, responsivo y moderno.

## 🗂️ Estructura del Proyecto
* `src/components/`: Componentes UI reutilizables (`GameCard`, `Navbar`, `HeroSection`).
* `src/layouts/`: Estructuras maestras (Metadatos, SEO, Footer global).
* `src/pages/`: Rutas de la aplicación web (`index`, `catalogo`, `checkout`, `admin`, etc.).
* `src/lib/`: Comunicación con la API REST (`api.ts`), manejo del carrito y tokens JWT en el LocalStorage.
* `src/styles/`: Estilos globales y directivas de Tailwind.

## 🚀 Instalación y Ejecución

1. **Instalar dependencias:**
   Puedes utilizar `npm`, `yarn`, `pnpm` o `bun`.
   ```bash
   npm install
   npm run dev
