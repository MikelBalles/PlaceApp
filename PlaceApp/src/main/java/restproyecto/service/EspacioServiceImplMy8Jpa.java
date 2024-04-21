package restproyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.dto.ExtraDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Usuario;
import restproyecto.modelo.repository.EspacioRepository;
import restproyecto.modelo.repository.ExtraRepository;
import restproyecto.modelo.repository.ProvinciaRepository;
import restproyecto.modelo.repository.UsuarioRepository;



@Service
public class EspacioServiceImplMy8Jpa implements EspacioService{
	@Autowired
	private EspacioRepository erepo;
	@Autowired
	private UsuarioRepository urepo;

	@Autowired
	private ExtraRepository exrepo;
	
	
	@Override
	public List<Espacio> buscarTodosEspacios() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}

  @Override
    public Espacio darAltaEspacio(Espacio espacio) {
		try {
			return erepo.save(espacio);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

	
	

	@Override
	public List<Espacio> obtenerEspaciosDeUsuario(String username) {
		// TODO Auto-generated method stub
		return erepo.buscarPorUsuario(username);
	}

	@Override
	public Espacio buscarPorId(int idEspacio) {
		// TODO Auto-generated method stub
		return erepo.findById(idEspacio).orElse(null);
	}
}


	

		
	
	


