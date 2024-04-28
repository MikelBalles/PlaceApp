package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva,Integer>{
	////todo 
	@Query("SELECT new restproyecto.modelo.dto.ReservaEspacioDto(r.espacio.nombre, r.fechaInicio, r.fechaFin, r.precioVenta) " +
		       "FROM Reserva r " +
		       "WHERE r.espacio.id = :idEspacio")
		
	 	List<ReservaEspacioDto> findByEspacioId(int idEspacio);
	
	@Query("Select r From Reserva r where r.usuario.username = ?1")
		List<Reserva>FindReservaByUsuario(String Username);
}
