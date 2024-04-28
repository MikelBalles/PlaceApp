package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.repository.ReservaRepository;

@Service

public class ReservaServiceImplMy8Jpa implements ReservaService {
	@Autowired
	private ReservaRepository rrepo;
//metodo para poder sacar las reservas asociadas a un espacio pasandole el id del espacio 
	@Override
	public List<ReservaEspacioDto> obtenerReservaPorEspacio(int idEspacio) {
        return rrepo.findByEspacioId(idEspacio);
	}
	@Override
	public Reserva buscarPorId(int idReserva) {
		// TODO Auto-generated method stub
		return rrepo.findById(idReserva).orElse(null);
	}
	@Override
	public List<Reserva> obtenerReservaPorUsuario(String username) {
		// TODO Auto-generated method stub
		return rrepo.FindReservaByUsuario(username);
	}

	


	

}
