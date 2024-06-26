package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Reserva;

public interface ReservaService {
	
	
	List<Reserva> obtenerReservaPorEspacio(int idEspacio);
	Reserva buscarPorId(int idReserva);

	List<Reserva> obtenerReservaPorUsuario(String username);
	Reserva altaReserva(Reserva reserva);
	
	List<Reserva> buscarTodas();
	
	Boolean modificarReserva(Reserva reserva);
	
}
