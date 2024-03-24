package restproyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.repository.TipoRepository;


@Repository

public class TipoServiceImplMy8Jpa implements TipoService {
	@Autowired
	private TipoRepository trepo;
	

}
