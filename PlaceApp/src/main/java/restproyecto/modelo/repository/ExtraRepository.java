package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.entities.Extra;

public interface ExtraRepository extends JpaRepository<Extra, Integer> {
	
	
	 Extra findById(int idExtra);
	 
	 @Query("Select e from Extra e WHERE e.espacio.idEspacio = ?1")
	 List<Extra> buscarExtraPorEspacio(int idEspacio);
}
