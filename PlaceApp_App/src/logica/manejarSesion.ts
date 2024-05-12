import { KEY_SESSION_STORAGE } from "../datos/constantes";
import { sesionIniciada } from "../datos/tipos";

export function iniciarSesion(datos: sesionIniciada) {
    sessionStorage.setItem(KEY_SESSION_STORAGE, JSON.stringify(datos));
}

export function cerrarSesion() {
    sessionStorage.removeItem(KEY_SESSION_STORAGE);
}

export function obtenerSesion(): sesionIniciada | undefined {
    const datos = sessionStorage.getItem(KEY_SESSION_STORAGE);
    if (datos) {
        return JSON.parse(datos);
    }
    return undefined;
}