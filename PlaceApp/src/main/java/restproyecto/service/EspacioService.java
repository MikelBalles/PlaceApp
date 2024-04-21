package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;

public interface EspacioService {
	
	 Espacio darAltaEspacio(EspacioDto espacioDto);
	
	List<Espacio> buscarTodosEspacios();
	

	
	List<Espacio> obtenerEspaciosDeUsuario(String username);
}
