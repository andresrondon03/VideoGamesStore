# Frontend VideoGamesStore (Astro + Tailwind)

Frontend para la tienda de videojuegos con:
- Astro 6
- Tailwind CSS 4
- Content Collections para contenido editorial
- Integracion con backend Spring Boot (API REST + JWT)

## Configuracion

1. Instala dependencias:
   - `npm install`
2. Crea `.env` a partir de `.env.example`:
   - `PUBLIC_API_BASE_URL=http://localhost:8080`
3. Ejecuta en desarrollo:
   - `npm run dev`

## Rutas principales

- `/` landing page
- `/catalogo` busqueda y filtros conectados a `/api/videojuegos`
- `/registro` y `/login` para autenticacion
- `/checkout` para enviar carrito a `/api/pedidos/checkout`
- `/historial` para consultar pedidos del usuario
- `/admin` para resumen de inventario y pedidos admin

## Integracion backend

Endpoints consumidos:
- `GET /api/videojuegos`
- `POST /api/auth/registro`
- `POST /api/auth/login`
- `POST /api/pedidos/checkout`
- `GET /api/pedidos/historial`
- `GET /api/pedidos/admin/todos`

JWT:
- El token devuelto por login se guarda en `localStorage`.
- Se envia como `Authorization: Bearer <token>` para endpoints protegidos.

## Nota CORS para Spring Boot

El backend ya tiene `http.cors()` en seguridad. Si el navegador bloquea peticiones desde Astro (`http://localhost:4321`), agrega una configuracion de origen permitido para ese host en backend.
