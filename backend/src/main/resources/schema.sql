DROP TABLE IF EXISTS detalle_pedido, vj_cat, vj_plat, pedidos, videojuegos, categorias, plataformas, usuarios, roles CASCADE;

-- roles --
CREATE TABLE roles (
    id_rol SERIAL PRIMARY KEY,
    rol VARCHAR(20) UNIQUE NOT NULL,
    descripcion VARCHAR(100)
);

-- usuarios --
CREATE TABLE usuarios (
    id_user SERIAL PRIMARY KEY, 
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    rol INT NOT NULL,
    CONSTRAINT user_FK_roles FOREIGN KEY (rol) REFERENCES roles(id_rol)
);

-- plataformas --
CREATE TABLE plataformas (
    id_plat SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- categorias --
CREATE TABLE categorias (
    id_cat SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- videojuegos --
CREATE TABLE videojuegos (
    id_vj SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    stock INT NOT NULL CHECK (stock >= 0),
    precio_base DECIMAL(10,2) NOT NULL,
    descuento INT DEFAULT 0 CHECK (descuento >= 0 AND descuento <= 100),
    imagen_url TEXT
);

-- pedidos --
CREATE TABLE pedidos (
    id_ped SERIAL PRIMARY KEY,
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('Completado', 'Cancelado')),
    id_user INT NOT NULL,
    CONSTRAINT user_fk_pedido FOREIGN KEY (id_user) REFERENCES usuarios(id_user) ON DELETE CASCADE
);

-- detalle_pedido --
CREATE TABLE detalle_pedido (
    id_detalle SERIAL PRIMARY KEY,
    id_ped INT NOT NULL,
    id_vj INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedido FOREIGN KEY (id_ped) REFERENCES pedidos(id_ped) ON DELETE CASCADE,
    CONSTRAINT fk_videojuego FOREIGN KEY (id_vj) REFERENCES videojuegos(id_vj) ON DELETE RESTRICT
);

-- Relaciones Muchos a Muchos --
CREATE TABLE vj_cat (
    id_vj INT REFERENCES videojuegos(id_vj) ON DELETE CASCADE,
    id_cat INT REFERENCES categorias(id_cat) ON DELETE CASCADE,
    PRIMARY KEY (id_vj, id_cat)
);

CREATE TABLE vj_plat (
    id_vj INT REFERENCES videojuegos(id_vj) ON DELETE CASCADE,
    id_plat INT REFERENCES plataformas(id_plat) ON DELETE CASCADE,
    PRIMARY KEY (id_vj, id_plat)
);