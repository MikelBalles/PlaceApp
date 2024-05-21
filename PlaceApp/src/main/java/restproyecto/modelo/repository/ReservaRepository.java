package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva,Integer>{
	@Query("SELECT r FROM Reserva r where r.espacio.id = ?1")
	 	List<Reserva> findByEspacioId(int idEspacio);
	
	@Query("Select r From Reserva r where r.usuario.username = ?1")
		List<Reserva>FindReservaByUsuario(String Username);
}
