package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Provincia;

public interface ProvinciaRepository extends JpaRepository <Provincia,Integer> {

	@Query("SELECT p FROM Provincia p")
	List<Provincia> buscarProvincias();
	
	
}
