package restproyecto.service;

import java.util.List;

import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;

public interface EspacioService {
	
	 Espacio darAltaEspacio(Espacio espacio);
	
	List<Espacio> buscarTodosEspacios();
	
	Espacio buscarPorId(int idEspacio);
	
	List<Espacio> obtenerEspaciosDeUsuario(String username);
	
	List <Espacio>buscarEspaciosSubtipoProvincia(int idSubtipo,int idProv);
}
