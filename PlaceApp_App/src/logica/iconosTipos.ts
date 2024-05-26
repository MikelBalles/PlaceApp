import icon_deportivo from '../assets/Deportivo.svg';
import icon_privadas from '../assets/Salas_privadas.svg';
import icon_eventos from '../assets/Grandes_eventos.svg';
import icon_trabajo from '../assets/Trabajo.svg';
import icon_coworking from '../assets/Coworking.svg';
import icon_conferencias from '../assets/sala_conferencias.svg';
import icon_salon_actos from '../assets/salon_actos.svg';
import icon_polivalente from '../assets/sala_polivalente.svg';
import icon_baloncesto from '../assets/icon_baloncesto.svg';
import icon_padel from '../assets/ico-tenis-padel.svg';
import icon_futbol from '../assets/ico-futbol.svg';

export const obtenerImagenSubtipo = (idSubtipo: number): string => {
    switch (idSubtipo) {
        case 1:
            return icon_coworking;
        case 2:
            return icon_conferencias;
        case 3:
            return icon_salon_actos;
        case 4:
            return icon_baloncesto;
        case 5:
            return icon_padel;
        case 6:
            return icon_futbol;
        case 7:
            return icon_polivalente;
        default:
            return '';
    }
}
export const obtenerImagenPorNombre = (nombreTipo: string): string => {
    switch (nombreTipo) {
        case 'Espacios Deportivos':
            return icon_deportivo;
        case 'Grandes eventos':
            return icon_eventos;
        case 'Salas privadas':
            return icon_privadas;
        case 'Trabajo':
            return icon_trabajo;
        default:
            return '';
    }
};