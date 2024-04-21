package restproyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproyecto.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario,String>{

	
	
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario findUsuarioByusername(String username);
}
