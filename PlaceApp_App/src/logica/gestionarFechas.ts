interface Reserva { inicio: Date, fin: Date }

const generarHorasDelDia = (): Date[] => {
  const horas: Date[] = [];
  const ahora = new Date();
  ahora.setMinutes(0, 0, 0);

  for (let i = 8; i <= 23; i++) {
    const hora = new Date(ahora);
    hora.setHours(i);
    horas.push(hora);
  }

  return horas;
};

const filtrarHorasPasadas = (horas: Date[]): Date[] => {
  const ahora = new Date();
  return horas.filter(hora => hora > ahora);
};

const filtrarHorasReservadas = (horas: Date[], reservas: Reserva[]): Date[] => {
  return horas.filter(hora => {
    for (const reserva of reservas) {
      if (hora >= reserva.inicio && hora <= reserva.fin) {
        return false;
      }
    }
    return true;
  });
};


 export const obtenerHorasDisponibles = (reservas: Reserva[]): Date[] => {
  let horas = generarHorasDelDia();
  horas = filtrarHorasPasadas(horas);
  horas = filtrarHorasReservadas(horas, reservas);
  return horas;
};

