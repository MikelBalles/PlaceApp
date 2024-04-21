package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.repository.ExtraRepository;

@Service
public class ExtraServiceImplMy8Jpa implements ExtraService {
	
	@Autowired
	ExtraRepository exrepo;

	@Override
	public List<Extra> buscarExtraPorEspacio(int idEspacio) {
		
		return exrepo.buscarExtraPorEspacio(idEspacio);
	}

	@Override
	public Extra darAltaExtra(Extra extra) {
		try {
			return exrepo.save(extra);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

}
