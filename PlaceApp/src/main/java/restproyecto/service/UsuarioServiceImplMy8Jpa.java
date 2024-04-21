package restproyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.entities.Usuario;
import restproyecto.modelo.repository.UsuarioRepository;


@Repository
public class UsuarioServiceImplMy8Jpa implements UsuarioService {

	@Override
	public List<Usuario> obtenerUsuarioPorId(int username) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
