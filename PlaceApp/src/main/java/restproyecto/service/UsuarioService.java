package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Usuario;

public interface UsuarioService {
	Usuario obtenerUsuarioPorId(String username);
	
	Usuario login(String username, String password);

	boolean actualizarUsuario (Usuario usuario);
	
}
