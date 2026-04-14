DROP TABLE IF EXISTS roles, usuarios, plataformas, categorias, videojuegos, pedidos, vj_cat, vj_plat;

-- roles --
CREATE TABLE roles (
	id_rol SERIAL PRIMARY KEY,
	rol VARCHAR(10),
	descripcion VARCHAR(50)
);

-- usuarios--
CREATE TABLE usuarios (
	id_user SERIAL PRIMARY KEY, 
	nickname VARCHAR(15) NOT NULL,
	email VARCHAR(30) NOT NULL,
	hashed_password VARCHAR(255) NOT NULL,
	rol INT NOT NULL,
	CONSTRAINT user_FK_roles FOREIGN KEY (rol) REFERENCES roles(id_rol)
);

-- plataformas---
CREATE TABLE plataformas (
	id_plat SERIAL PRIMARY KEY,
	nombre VARCHAR(15) NOT NULL
);

-- categorias--
CREATE TABLE categorias(
	id_cat SERIAL PRIMARY KEY,
	nombre VARCHAR(15) NOT NULL
);

--videojuegos--
CREATE TABLE videojuegos (
	id_vj SERIAL PRIMARY KEY,
	titulo VARCHAR(50) NOT NULL,
	descripcion VARCHAR(50),
	stock INT NOT NULL,
	precio_base REAL NOT NULL
);

--pedidos
CREATE TABLE pedidos(
    id_ped SERIAL PRIMARY KEY,
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2),
	estado VARCHAR(20) NOT NULL CHECK (estado IN ('Completado', 'Cancelado')),
    id_user INT NOT NULL,
    
    CONSTRAINT user_fk_pedido
        FOREIGN KEY (id_user) 
        REFERENCES usuarios(id_user)
        ON DELETE CASCADE -- Si borras al usuario, se borran sus pedidos
);

-- 1:M videojuegos con categorias 
CREATE TABLE vj_cat (
    id_vj INT REFERENCES videojuegos(id_vj),
    id_cat INT REFERENCES categorias(id_cat),
    PRIMARY KEY (id_vj, id_cat)
);

-- 1:M videojuegos con plataformas
CREATE TABLE vj_plat (
    id_vj INT REFERENCES videojuegos(id_vj),
    id_plat INT REFERENCES plataformas(id_plat),
    PRIMARY KEY (id_vj, id_plat)
);

