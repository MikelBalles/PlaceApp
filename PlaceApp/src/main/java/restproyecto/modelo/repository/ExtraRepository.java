package restproyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproyecto.modelo.entities.Extra;

public interface ExtraRepository extends JpaRepository<Extra, Integer> {
	
	
	 Extra findById(int idExtra);
}
