-- Inserción de Roles
INSERT INTO roles (id_rol, rol, descripcion) VALUES 
(1, 'ADMIN', 'Administrador del sistema'), 
(2, 'CLIENTE', 'Cliente regular de la tienda')
ON CONFLICT (id_rol) DO NOTHING;

-- Inserción de Categorías y Plataformas
INSERT INTO categorias (id_cat, nombre) VALUES (1, 'Acción'), (2, 'Aventura'), (3, 'RPG');
INSERT INTO plataformas (id_plat, nombre) VALUES (1, 'PC'), (2, 'PS5'), (3, 'Xbox Series');

-- Inserción de Videojuego Inicial
INSERT INTO videojuegos (id_vj, titulo, descripcion, stock, precio_base) 
VALUES (1, 'Cyber Night 2077', 'RPG de mundo abierto futurista', 10, 59.99);

-- Relaciones en tablas pivote (Muchos a Muchos)
INSERT INTO vj_cat (id_vj, id_cat) VALUES (1, 3);
INSERT INTO vj_plat (id_vj, id_plat) VALUES (1, 1), (1, 2);

-- Ajuste de secuencias
SELECT setval('roles_id_rol_seq', (SELECT MAX(id_rol) FROM roles));
SELECT setval('categorias_id_cat_seq', (SELECT MAX(id_cat) FROM categorias));
SELECT setval('plataformas_id_plat_seq', (SELECT MAX(id_plat) FROM plataformas));
SELECT setval('videojuegos_id_vj_seq', (SELECT MAX(id_vj) FROM videojuegos));