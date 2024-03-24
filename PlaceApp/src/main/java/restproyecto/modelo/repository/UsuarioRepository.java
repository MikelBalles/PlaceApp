package restproyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproyecto.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario,String>{

}
