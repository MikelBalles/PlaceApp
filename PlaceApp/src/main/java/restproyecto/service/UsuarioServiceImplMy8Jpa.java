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

	@Override
	public Usuario login(String username, String password) {
		// TODO Auto-generated method stub
		return urepo.usuarioPorUsernameAndPassword(username, password);
	}

	 @Override
	    public boolean actualizarUsuario(Usuario usuario) {
	        try {
	            urepo.save(usuario); // Guardar los cambios en la base de datos
	            return true; // La actualización fue exitosa
	        } catch (Exception e) {
	            // Manejar cualquier excepción y registrar errores si es necesario
	            return false; // La actualización falló
	        }
	    }

}
