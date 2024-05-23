package restproyecto.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.EspacioClienteDto;
import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.service.EspacioService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
/**
 * Controlador REST para las operaciones relacionadas con las reservas y gestión de espacios comunes entre clientes y propietarios.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/comun")
public class ComunesRestController {
	

	/**
     * Servicio para gestionar la información de los espacios.
     */
	@Autowired
	private EspacioService espacioService;
	
	/**
     * Servicio para gestionar la información de las provincias.
     */
	@Autowired
	private ProvinciaService provinciaService;
	
	/**
     * Servicio para gestionar la información de los subtipos de espacios.
     */
	@Autowired
	private SubtipoService subtipoService;
	
	/**
     * Servicio para gestionar la información de las reservas.
     */
	@Autowired
	private ReservaService reservaService;
	

	
	
	 /**
    * Peticion a la base de datos que obtiene los espacios filtrando por un subtipo y una provincia.
    *
    * @param idSubtipo Identificador del subtipo de espacio.
    * @param idProv    Identificador de la provincia.
    * @return Lista de espacios filtrados.
    */
	
	
	@GetMapping("/espacio/subtipo/provincia/{idSubtipo}/{idProv}")
	public List<EspacioClienteDto> buscarPorSubtipoyProv(@PathVariable int idSubtipo, @PathVariable int idProv) {
	    List<Espacio> espacios = espacioService.buscarEspaciosSubtipoProvincia(idSubtipo, idProv);
	    List<EspacioClienteDto> espaciosDto = new ArrayList<>();

	    for (Espacio e : espacios) {
	        EspacioClienteDto eDto = new EspacioClienteDto();
	        eDto.setIdEspacio(e.getIdEspacio());
	        eDto.setNombre(e.getNombre());
	        eDto.setDescripcion(e.getDescripcion());
	        eDto.setDireccion(e.getDireccion());
	        eDto.setPrecio(e.getPrecio());
	        espaciosDto.add(eDto);
	    }

	    return espaciosDto;
	}
		
	

	
	/**
     * Consulta a la base de datos que obtiene todas las provincias disponibles.
     *
     * @return Lista de todas las provincias.
     */
	@GetMapping("/provincias")
	public List <Provincia> todasProvincias (){
		return provinciaService.buscarProvincias();
	}
	
	/**
     * Consulta a la base de datos que obtiene todos los subtipos disponibles.
     *
     * @return Lista de todos los subtipos.
     */
	@GetMapping("/subtipo")
	public List <Subtipo> todosSubtipo (){
		return subtipoService.buscarSubtipo();
	}
	
	/**
     * Consulta a la base de datos que obtiene los subtipos asociados a un espacio.
     *
     * @param idEspacio Identificador del espacio.
     * @return Lista de subtipos asociados al espacio.
     */
	@GetMapping("/subtipo/espacio/{idEspacio}")
	public List<SubtipoEspacioDto> obtenerSubtipoPorEspacio(@PathVariable int idEspacio) {
        return subtipoService.obtenerSubtipoPorEspacio(idEspacio);
	
	  }
	
    	
   	 /**
        * Consulta a la base de datos que obtiene las reservas asociadas a un espacio.
        *
        * @param idEspacio Identificador del espacio.
        * @return Lista de reservas asociadas al espacio.
        */
   	@GetMapping("/reservas/espacio/{idEspacio}")
   	public List<ReservaEspacioDto> obtenerReservasPorEspacio(@PathVariable int idEspacio) {
           return reservaService.obtenerReservaPorEspacio(idEspacio);

   }
   	
   	
   	/**
        * Consutla a la base de datos que obtiene todos los espacios disponibles.
        *
        * @return Lista de todos los espacios.
        */
   	@GetMapping("/espacio")
   	public List <Espacio> todosEspacios (){
   		return espacioService.buscarTodosEspacios();
   	}
	
	
	
	
    	
	
   	
	
	
	
}
