package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.entities.Usuario;

public interface UsuarioService {
	List<Usuario> obtenerUsuarioPorId(int username);
}
