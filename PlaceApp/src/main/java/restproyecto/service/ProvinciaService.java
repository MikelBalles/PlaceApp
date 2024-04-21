package restproyecto.service;
import java.util.List;

import restproyecto.modelo.entities.Provincia;
public interface ProvinciaService {
	
	List<Provincia> buscarProvincias();
	Provincia  buscarPorId(int idProv);

}
