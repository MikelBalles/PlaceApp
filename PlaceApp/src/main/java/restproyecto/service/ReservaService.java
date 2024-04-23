package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Reserva;

public interface ReservaService {
	
	
	List<ReservaEspacioDto> obtenerReservaPorEspacio(int idEspacio);
	Reserva buscarPorId(int idReserva);
    List<Reserva>obtenerReservaUsuario(int idReserva);

}
