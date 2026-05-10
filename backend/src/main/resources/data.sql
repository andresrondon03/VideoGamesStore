-- Insertar Roles --
INSERT INTO roles (rol, descripcion) VALUES 
('ADMIN', 'Administrador con acceso total'),
('USER', 'Cliente de la tienda');

-- Insertar Usuarios (Password de ejemplo: "123456" hashed) --
-- Nota: En producción, usa el hash generado por tu BCryptPasswordEncoder --
INSERT INTO usuarios (nickname, email, hashed_password, rol) VALUES 
('admin_tech', 'admin@techzone.com', '$2a$10$8.UnVuG9HHgffUDAlk8qONu5CL67yB.mZqjZuaV3V5KzE5E8.p9y6', 1),
('santiago_dev', 'santiago@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qONu5CL67yB.mZqjZuaV3V5KzE5E8.p9y6', 2);

-- Insertar Categorías --
INSERT INTO categorias (nombre) VALUES ('Acción'), ('RPG'), ('Aventura'), ('Deportes'), ('Estrategia');

-- Insertar Plataformas --
INSERT INTO plataformas (nombre) VALUES ('PC'), ('PS5'), ('Xbox Series X'), ('Nintendo Switch');

-- Insertar Videojuegos con Imágenes y Descuentos --
INSERT INTO videojuegos (titulo, descripcion, stock, precio_base, descuento, imagen_url) VALUES 
('Elden Ring', 'Explora las Tierras Intermedias en este RPG de acción épico.', 15, 59.99, 10, 'https://store-images.s-microsoft.com/image/apps.25322.14537704372270848.6ecb6038-5426-409a-8660-158d1eb64fb0.d230176a-d7a2-4696-ad23-ff53a6e004df'),
('Cyberpunk 2077', 'Un RPG de acción y aventura de mundo abierto ambientado en Night City.', 20, 49.99, 20, 'https://store-images.s-microsoft.com/image/apps.47379.63407868131364914.bcaa868c-407e-42c2-baeb-48a3c9f29b54.89bb995b-b066-4a53-9fe4-0260ce07e894'),
('FC 24', 'La nueva era del juego del mundo con licencias oficiales.', 50, 69.99, 0, 'https://store-images.s-microsoft.com/image/apps.62211.13743940396065041.b843213c-0751-4e6c-8c85-83412bf1f1aa.04161b63-31e2-4743-b77b-cdb3fb3dccd6'),
('Hollow Knight', 'Una aventura de acción clásica en 2D en un vasto mundo interconectado.', 35, 14.99, 0, 'https://m.media-amazon.com/images/M/MV5BMGIyYmJmZDgtOWQ1Ny00NDFiLTk2OTgtM2Q2ZWQ4OWIxZjg3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg');

-- Asociar Videojuegos con Categorías (vj_cat) --
INSERT INTO vj_cat (id_vj, id_cat) VALUES 
(1, 2), -- Elden Ring -> RPG
(2, 1), (2, 2), -- Cyberpunk -> Acción, RPG
(3, 4), -- FC 24 -> Deportes
(4, 3); -- Hollow Knight -> Aventura

-- Asociar Videojuegos con Plataformas (vj_plat) --
INSERT INTO vj_plat (id_vj, id_plat) VALUES 
(1, 1), (1, 2), (1, 3), -- Elden Ring en PC, PS5, Xbox
(2, 1), (2, 2), -- Cyberpunk en PC, PS5
(3, 2), (3, 3), -- FC 24 en PS5, Xbox
(4, 1), (4, 4); -- Hollow Knight en PC, Switch