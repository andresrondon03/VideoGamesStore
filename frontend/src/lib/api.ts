import type {
  FiltrosCatalogo,
  LoginRequest,
  Pedido,
  PedidoRequest,
  RegistroRequest,
  Videojuego,
} from "./types";

const API_BASE_URL = import.meta.env.PUBLIC_API_BASE_URL ?? "http://localhost:8080";

export class ApiError extends Error {
  status: number;

  constructor(message: string, status: number) {
    super(message);
    this.status = status;
  }
}

async function request<T>(path: string, options: RequestInit = {}): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(options.headers ?? {}),
    },
  });

  const contentType = response.headers.get("content-type") ?? "";
  const isJson = contentType.includes("application/json");
  const payload = isJson ? await response.json() : await response.text();

  if (!response.ok) {
    const errorMessage = typeof payload === "string" ? payload : payload.message ?? "Error inesperado";
    throw new ApiError(errorMessage, response.status);
  }

  return payload as T;
}

function buildQuery(filters?: FiltrosCatalogo): string {
  if (!filters) return "";
  const params = new URLSearchParams();
  Object.entries(filters).forEach(([key, value]) => {
    if (value !== undefined && value !== null && value !== "") {
      params.set(key, String(value));
    }
  });
  const query = params.toString();
  return query ? `?${query}` : "";
}

export const api = {
  videojuegos: {
    listar: (filters?: FiltrosCatalogo) =>
      request<Videojuego[]>(`/api/videojuegos${buildQuery(filters)}`),
    crear: (token: string, payload: Partial<Videojuego>) =>
      request<Videojuego>("/api/videojuegos", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(payload),
      }),
    actualizar: (token: string, id: number, payload: Partial<Videojuego>) =>
      request<Videojuego>(`/api/videojuegos/${id}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(payload),
      }),
    eliminar: (token: string, id: number) =>
      request<void>(`/api/videojuegos/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }),
  },
  auth: {
    registro: (input: RegistroRequest) =>
      request<Record<string, unknown>>("/api/auth/registro", {
        method: "POST",
        body: JSON.stringify(input),
      }),
    login: (input: LoginRequest) =>
      request<string>("/api/auth/login", {
        method: "POST",
        body: JSON.stringify(input),
      }),
  },
  pedidos: {
    checkout: (token: string, payload: PedidoRequest) =>
      request<Pedido>("/api/pedidos/checkout", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(payload),
      }),
    historial: (token: string) =>
      request<Pedido[]>("/api/pedidos/historial", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }),
    todosAdmin: (token: string) =>
      request<Pedido[]>("/api/pedidos/admin/todos", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }),
  },
  categorias: {
    listar: () => request<any[]>("/api/categorias"),
  },
  plataformas: {
    listar: () => request<any[]>("/api/plataformas"),
  },
};

export { API_BASE_URL };
