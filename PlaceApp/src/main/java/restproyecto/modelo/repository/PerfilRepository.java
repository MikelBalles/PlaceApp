package restproyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproyecto.modelo.entities.Perfil;

public interface PerfilRepository extends JpaRepository <Perfil,Integer> {

}
