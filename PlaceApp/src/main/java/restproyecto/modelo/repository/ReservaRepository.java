package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva,Integer>{
	
	@Query("SELECT new restproyecto.modelo.dto.ReservaEspacioDto(r.espacio.nombre, r.fechaInicio, r.fechaFin, r.precioVenta) " +
		       "FROM Reserva r " +
		       "WHERE r.espacio.id = :idEspacio")
		
	 List<ReservaEspacioDto> findByEspacioId(int idEspacio);
	
	 @Query("Select r from Reserva r WHERE r.idReserva = ?1")
	 List<Reserva> buscarReservaUsuarioReserva(int idReserva);

}
