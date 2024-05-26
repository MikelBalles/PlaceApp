import { Reserva, horaConDisponibilidad } from "../datos/tipos";

const generarHorasDelDia = (diaParam: Date): Date[] => {
  const horas: Date[] = [];
  const ahora = new Date();
  diaParam.setMinutes(0, 0, 0);
  diaParam.setHours(0, 0, 0, 0);
  ahora.setMinutes(0, 0, 0);

  let dia = new Date();

  dia = (diaParam <= ahora) ? ahora : diaParam;

  for (let i = 8; i <= 23; i++) {
    const hora = new Date(dia);
    hora.setHours(i);
    horas.push(hora);
  }

  return horas;
};

/**
 * Comprueba si dos fechas son el mismo día. Lo necesitamos porque de bbdd llegan
 * reservas de todso los días y necesitamos filtrarlas.
 * @param fecha1 La primera fecha a comparar.
 * @param fecha2 La segunda fecha a comparar.
 * @returns Devuelve true si las fechas son el mismo día, de lo contrario devuelve false.
 */
const sonMismoDia = (fecha1: Date, fecha2: Date) => {
  return fecha1.getDate() === fecha2.getDate() &&
         fecha1.getMonth() === fecha2.getMonth() &&
         fecha1.getFullYear() === fecha2.getFullYear();
};

export const obtenerHorasDisponiblesYNoDisponibles = (dia:Date, reservas: Reserva[]): horaConDisponibilidad[] => {
  const horas = generarHorasDelDia(dia);
  const ahora = new Date();
  const ahoraSinHora = new Date();
  ahoraSinHora.setHours(0, 0, 0, 0);

  const horaObj:horaConDisponibilidad[] = [];
  
  if (dia < ahoraSinHora) {
    return horaObj;
  } 

  for (const hora of horas) {
    let disponible = true;
    for (const reserva of reservas) {
      const fechaInicio = new Date(reserva.fechaInicioReserva);
      const fechaFin = new Date(reserva.fechaFinReserva);

      if (sonMismoDia(fechaInicio, dia)) {
        
        if ((hora >= fechaInicio && hora < fechaFin)) {
          disponible = false;
          break;
        }
      }
    }
    if (disponible && (dia < ahora)) {
      disponible = hora > ahora;
    }    
    horaObj.push({ horaDate:hora, disponible });
  }

  return horaObj;
};

export const obtenerMaxHorasReservables = (horaInicio: number, horas:horaConDisponibilidad[]) => {

  // Encuentra la posición de la hora de inicio en el array de horas
  const inicioIndex = horas.findIndex(hora => hora.horaDate.getHours() === horaInicio);

  // Si la hora de inicio no está en el array de horas, devuelve 0
  if (inicioIndex === -1) {
    return 0;
  }

  if (!horas[inicioIndex].disponible) {
    return 0;
  }

  // Calcula el número máximo de horas consecutivas que se pueden reservar
  let maxHoras = 0;

  for (let i = inicioIndex; i < horas.length; i++) {

    if (i > inicioIndex && (horas[i].disponible === false)) {
      break;
    }
    maxHoras++;
  }

  return maxHoras;
};

