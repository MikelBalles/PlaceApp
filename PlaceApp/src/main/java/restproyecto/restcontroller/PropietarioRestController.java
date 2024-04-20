package restproyecto.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;

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
	
	
	@GetMapping("/provincias")
	public List <Provincia> todasProvincias (){
		return provinciaService.buscarProvincias();
	}
	
	
	@GetMapping("/subtipo")
	public List <Subtipo> todosSubtipo (){
		return subtipoService.buscarSubtipo();
	}
	
	
	@GetMapping("/subtipo/espacio/{idEspacio}")
	public List<SubtipoEspacioDto> obtenerSubtipoPorEspacio(@PathVariable int idEspacio) {
        return subtipoService.obtenerSubtipoPorEspacio(idEspacio);
	
	}
	
	@GetMapping("/espacio/{idEspacio}")
	public List<ReservaEspacioDto> obtenerReservasPorEspacio(@PathVariable int idEspacio) {
        return reservaService.obtenerReservaPorEspacio(idEspacio);

}
}