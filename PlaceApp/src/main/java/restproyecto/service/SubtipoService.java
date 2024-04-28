package restproyecto.service;
import java.util.List;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;

import restproyecto.modelo.entities.Subtipo;
public interface SubtipoService {
	
	
		
	List<Subtipo> buscarSubtipo();
	
	List<SubtipoEspacioDto> obtenerSubtipoPorEspacio(int idEspacio);
	
	Subtipo buscarPorId(int idSubtipo);
	

}
