package restproyecto.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.modelo.entities.Usuario;
import restproyecto.service.EspacioService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/reserva")

public class PropietarioRestController {
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SubtipoService subtipoService;
	@Autowired
	private EspacioService espacioService;
	
	/////Mostrar toda las provincias
	@GetMapping("/provincias")
	public List <Provincia> todasProvincias (){
		return provinciaService.buscarProvincias();
	}
	
	/////Mostrar todo los subtipos 
	@GetMapping("/subtipo")
	public List <Subtipo> todosSubtipo (){
		return subtipoService.buscarSubtipo();
	}
	
	///Mostras Subtipo asociado a un espacio pasandole el id del espacio
	@GetMapping("/subtipo/espacio/{idEspacio}")
	public List<SubtipoEspacioDto> obtenerSubtipoPorEspacio(@PathVariable int idEspacio) {
        return subtipoService.obtenerSubtipoPorEspacio(idEspacio);
	
	}
	//////Mostar las reservas asociadas a un espacio pasandole el id del espacio 
	@GetMapping("/espacio/{idEspacio}")
	public List<ReservaEspacioDto> obtenerReservasPorEspacio(@PathVariable int idEspacio) {
        return reservaService.obtenerReservaPorEspacio(idEspacio);

}
	//// Mostrar todo los espacios 
	@GetMapping("/espacio")
	public List <Espacio> todosEspacios (){
		return espacioService.buscarTodosEspacios();
	}
	////Dar de alta un espacio
		
		@PostMapping("/espacio/alta")
		public Espacio crearEspacio(@RequestBody EspacioDto espacioDto) {
	        return espacioService.darAltaEspacio(espacioDto);
	    }
	//// Mostrar todo los espacios de un usuario  con el nombre del tipo del espacio 
		
	
	@GetMapping("espacio/usuario/{username}")
	public List<EspacioUsuarioDto> otenerEspacioDeUsuario(@PathVariable String username){
		List<Espacio> listaEspacios = new ArrayList<>();
		listaEspacios = espacioService.obtenerEspaciosDeUsuario(username);
		List<EspacioUsuarioDto> listaDto = new ArrayList<>();
		for (Espacio e:listaEspacios) {
			EspacioUsuarioDto dto = new EspacioUsuarioDto();
			dto.setIdEspacio(e.getIdEspacio());
			dto.setNombre(e.getNombre());
			dto.setDescripcion(e.getDescripcion());
			dto.setNombreTipoEspacio(e.getSubtipo().getNombre());
			dto.setPrecioPorHora(e.getPrecio());
			
			listaDto.add(dto);
		}
		
		return listaDto;
	}
		
	
	
	
	
	
	
	
	
}