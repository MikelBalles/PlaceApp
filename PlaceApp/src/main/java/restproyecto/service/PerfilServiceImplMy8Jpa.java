package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproyecto.modelo.entities.Perfil;
import restproyecto.modelo.repository.PerfilRepository;
@Service
public class PerfilServiceImplMy8Jpa implements PerfilService {
	
	@Autowired
	private PerfilRepository prepo;
	
	

	@Override
	public List<Perfil> buscarPerfiles() {
		// TODO Auto-generated method stub
		return prepo.findAll() ;
	}

}
