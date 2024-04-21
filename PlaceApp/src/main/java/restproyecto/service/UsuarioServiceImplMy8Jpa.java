package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.entities.Usuario;
import restproyecto.modelo.repository.UsuarioRepository;


@Repository
public class UsuarioServiceImplMy8Jpa implements UsuarioService {
	
	@Autowired
	UsuarioRepository urepo;

	@Override
	public Usuario obtenerUsuarioPorId(String username) {
		return urepo.findById(username).orElse(null);
	}

	

}
