package restproyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.entities.Espacio;

public interface EspacioRepository extends JpaRepository <Espacio,Integer> {

	@Query("Select e from Espacio e Where e.usuario.username=?1")
	    List<Espacio>buscarPorUsuario(String username);
	
	
	
	@Query("Select e from Espacio e Where e.subtipo.idSubtipo=?1 AND e.provincia.idProv =?2")
			List<Espacio>buscarEspacios(int idSubtipo,int idProv);




	@Query("Select e from Espacio e Where e.idEspacio=?1")
			Espacio buscarEspacioPorId(int idEspacio);
	

}








