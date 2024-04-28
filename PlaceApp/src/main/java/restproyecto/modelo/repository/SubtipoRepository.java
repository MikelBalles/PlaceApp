package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.dto.SubtipoEspacioDto;

import restproyecto.modelo.entities.Subtipo;

public interface SubtipoRepository extends JpaRepository<Subtipo, Integer>{
	
	
	@Query("SELECT s FROM Subtipo s")
	List<Subtipo> buscarSubtipo();
	
	
	 @Query("SELECT new restproyecto.modelo.dto.SubtipoEspacioDto(s.id, s.nombre) FROM Espacio e JOIN e.subtipo s WHERE e.id = :idEspacio")
	    List<SubtipoEspacioDto> findSubtipoByIdEspacio(int idEspacio);


	 
	 
	 
}
