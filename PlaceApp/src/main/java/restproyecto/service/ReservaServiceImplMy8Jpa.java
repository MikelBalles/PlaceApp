package restproyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.repository.ReservaRepository;

@Repository

public class ReservaServiceImplMy8Jpa implements ReservaService {
	@Autowired
	private ReservaRepository rrepo;
	

}
