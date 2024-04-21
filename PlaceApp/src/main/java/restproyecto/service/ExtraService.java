package restproyecto.service;

import java.util.List;

import restproyecto.modelo.entities.Extra;


public interface ExtraService {
	List<Extra> buscarExtraPorEspacio(int idEspacio);
	
	Extra darAltaExtra(Extra extra);

}
