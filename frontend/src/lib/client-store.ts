import type { ItemCarritoDTO } from "./types";

const TOKEN_KEY = "vgs_token";
const CART_KEY = "vgs_cart";

function safeJsonParse<T>(raw: string | null, fallback: T): T {
  if (!raw) return fallback;
  try {
    return JSON.parse(raw) as T;
  } catch {
    return fallback;
  }
}

export function getToken(): string {
  if (typeof localStorage === "undefined") return "";
  return localStorage.getItem(TOKEN_KEY) ?? "";
}

export function getJwtPayload(token: string): { email: string; rol: string } | null {
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const normalized = payloadBase64.replace(/-/g, "+").replace(/_/g, "/");
    const json = JSON.parse(atob(normalized));
    return {
      email: String(json.sub ?? ""),
      rol: String(json.rol ?? ""),
    };
  } catch {
    return null;
  }
}

export function setToken(token: string): void {
  if (typeof localStorage === "undefined") return;
  localStorage.setItem(TOKEN_KEY, token);
}

export function clearToken(): void {
  if (typeof localStorage === "undefined") return;
  localStorage.removeItem(TOKEN_KEY);
}

export function getCart(): ItemCarritoDTO[] {
  if (typeof localStorage === "undefined") return [];
  return safeJsonParse<ItemCarritoDTO[]>(localStorage.getItem(CART_KEY), []);
}

export function saveCart(items: ItemCarritoDTO[]): void {
  if (typeof localStorage === "undefined") return;
  localStorage.setItem(CART_KEY, JSON.stringify(items));
}

export function addToCart(idVideojuego: number): void {
  const cart = getCart();
  const current = cart.find((item) => item.idVideojuego === idVideojuego);
  if (current) {
    current.cantidad += 1;
  } else {
    cart.push({ idVideojuego, cantidad: 1 });
  }
  saveCart(cart);
}

export function clearCart(): void {
  if (typeof localStorage === "undefined") return;
  localStorage.removeItem(CART_KEY);
}
