export interface  Categoria {
  id?: number;
  nombre: string;
}

export interface Plataforma {
  id?: number;
  nombre: string;
}

export interface Videojuego {
  id: number;
  titulo: string;
  descripcion: string;
  precioBase: number;
  stock: number;
  categorias: Categoria[];
  plataformas: Plataforma[];
}

export interface RegistroRequest {
  nickname: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface ItemCarritoDTO {
  idVideojuego: number;
  cantidad: number;
}

export interface PedidoRequest {
  items: ItemCarritoDTO[];
}

export interface PedidoDetalle {
  id: number;
  cantidad: number;
  precioUnitario: number;
  videojuego: Videojuego;
}

export interface UsuarioLite {
  id: number;
  nickname: string;
  email: string;
}

export interface Pedido {
  id: number;
  fechaCompra: string;
  total: number;
  estado: string;
  usuario?: UsuarioLite;
  detalles: PedidoDetalle[];
}

export interface FiltrosCatalogo {
  titulo?: string;
  categoria?: string;
  plataforma?: string;
  precioMax?: number;
}
