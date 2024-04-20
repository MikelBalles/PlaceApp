package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.repository.ProvinciaRepository;
@Service
public class ProvinciaServiceImplMy8Jpa implements ProvinciaService{
	
	@Autowired
	private ProvinciaRepository prepo;

	@Override
	public List<Provincia> buscarProvincias() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

}
