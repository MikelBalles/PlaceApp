package restproyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproyecto.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva,Integer>{

}
