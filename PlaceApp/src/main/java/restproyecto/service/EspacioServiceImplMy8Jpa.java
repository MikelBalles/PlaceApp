package restproyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.repository.EspacioRepository;



@Repository
public class EspacioServiceImplMy8Jpa implements EspacioService{
	@Autowired
	private EspacioRepository erepo;
	

}
