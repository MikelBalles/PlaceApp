package restproyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import restproyecto.modelo.repository.UsuarioRepository;


@Repository
public class UsuarioServiceImplMy8Jpa implements UsuarioService {
	@Autowired
	UsuarioRepository urepo;
	

}
