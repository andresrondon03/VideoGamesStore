-- Inserción de Roles
INSERT INTO roles (id_rol, rol, descripcion) VALUES (1, 'ADMIN', 'Administrador'), (2, 'CLIENTE', 'Cliente');

-- Inserción de Categorías y Plataformas
INSERT INTO categorias (id_cat, nombre) VALUES (1, 'Acción'), (2, 'Aventura'), (3, 'RPG');
INSERT INTO plataformas (id_plat, nombre) VALUES (1, 'PC'), (2, 'PS5'), (3, 'Xbox Series');

-- Inserción de Videojuego Inicial
INSERT INTO videojuegos (id_vj, titulo, descripcion, stock, precio_base) 
VALUES (1, 'Cyber Night 2077', 'RPG de mundo abierto', 10, 59.99);

-- Relaciones en tablas pivote (Muchos a Muchos)
INSERT INTO vj_cat (id_vj, id_cat) VALUES (1, 3);
INSERT INTO vj_plat (id_vj, id_plat) VALUES (1, 1), (1, 2);