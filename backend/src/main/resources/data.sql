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
('Elden Ring', 'Explora las Tierras Intermedias en este RPG de acción épico.', 15, 59.99, 10, 'https://image.api.playstation.com/vulcan/ap/rnd/202110/2000/aajm89u9v6Y6XvXvXvXvXvXv.png'),
('Cyberpunk 2077', 'Un RPG de acción y aventura de mundo abierto ambientado en Night City.', 20, 49.99, 20, 'https://image.api.playstation.com/vulcan/ap/rnd/202111/3013/itXvXvXvXvXvXvXvXv.png'),
('FC 24', 'La nueva era del juego del mundo con licencias oficiales.', 50, 69.99, 0, 'https://image.api.playstation.com/vulcan/ap/rnd/202307/1012/6XvXvXvXvXvXvXv.png'),
('Hollow Knight', 'Una aventura de acción clásica en 2D en un vasto mundo interconectado.', 35, 14.99, 0, 'https://assets.nintendo.com/image/upload/ar_16:9,c_lpad,w_1240/b_white/f_auto/q_auto/ncom/software/switch/70010000003208/Hollow-Knight-Switch-Hero');

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