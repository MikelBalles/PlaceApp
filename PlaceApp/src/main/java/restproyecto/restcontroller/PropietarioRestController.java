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

import restproyecto.modelo.dto.AltaEspacioDto;
import restproyecto.modelo.dto.AltaExtraDto;
import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.dto.ExtraDto;
import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Perfil;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.modelo.entities.Usuario;
import restproyecto.service.EspacioService;
import restproyecto.service.ExtraService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
import restproyecto.service.UsuarioService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/reserva")

public class PropietarioRestController {
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SubtipoService subtipoService;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private ExtraService extraService;
	
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
	@GetMapping("reservas/espacio/{idEspacio}")
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
	public Espacio crearEspacio(@RequestBody AltaEspacioDto bodyDto) {
		
		//Comprobamos si el username es Rol propietario
		Usuario usu = usuarioService.obtenerUsuarioPorId(bodyDto.getUsername());
		Perfil perfil = usu.getPerfil();
		if (perfil.getIdPerfil() != 2) {
			return null;
		}
		
		//Construimos el Espacio para dar de Alta
		Espacio espacio = new Espacio();
			espacio.setNombre(bodyDto.getNombreEspacio());
			espacio.setDescripcion(bodyDto.getDescripcion());
			espacio.setDireccion(bodyDto.getDireccion());
			espacio.setCp(bodyDto.getCp());
			espacio.setEnabled(1);
			espacio.setPrecio(bodyDto.getPrecio());
			espacio.setProvincia(provinciaService.buscarPorId(bodyDto.getIdProv()));
			espacio.setSubtipo(subtipoService.buscarPorId(bodyDto.getIdSubtipo()));
			espacio.setUsuario(usu);
			
		//Damos de alta el espacio en BBDD
		Espacio espacioAlta = espacioService.darAltaEspacio(espacio);
		
		if (espacioAlta == null) {
			return espacioAlta;
		}
		
		//Añadimos los extras del espacio
		List<AltaExtraDto> listaExtrasDto = bodyDto.getExtras();
		
		for (AltaExtraDto ele : listaExtrasDto) {
			Extra extra = new Extra();
				extra.setNombre(ele.getNombre());
				extra.setPrecio(ele.getPrecio());
				extra.setDescripcion(ele.getDescripcion());
				extra.setEspacio(espacioAlta);
			
			//Damos de alta el extra
			extraService.darAltaExtra(extra);
		}
				
        return espacioAlta;
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
		
	@GetMapping("espacio/{idEspacio}")
	public EspacioDto obtenerDetalleEspacio (@PathVariable int idEspacio) {
		//Creamos el objeto que vamos a devolver
		EspacioDto eDto = new EspacioDto();
		//Creamos el Objeto espacio completo
		Espacio espacio = espacioService.buscarPorId(idEspacio);
		//Creamos la lista de Extras de ese espacio
		List<Extra> listaExtras = new ArrayList<>();
		List<ExtraDto> listaExtraDto = new ArrayList<>();	
		
		if (espacio == null)
			return eDto;
		
		//Sacamos la lista de extras del espacio
		listaExtras = extraService.buscarExtraPorEspacio(idEspacio);
		
		for (Extra extra : listaExtras) {
			ExtraDto dto = new ExtraDto();
			
			dto.setIdExtra(extra.getIdExtra());
			dto.setNombre(extra.getNombre());
			dto.setPrecio(extra.getPrecio());
			
			listaExtraDto.add(dto);
		}
		
		//Construimos el Dto a devolver
		eDto.setNombreEspacio(espacio.getNombre());
		eDto.setCp(espacio.getCp());
		eDto.setDescripcion(espacio.getDescripcion());
		eDto.setExtras(listaExtraDto);
		eDto.setProvincia(espacio.getProvincia().getNombre());
		eDto.setSubtipo(espacio.getSubtipo().getNombre());
		eDto.setPrecio(espacio.getPrecio());
		
		return eDto;
	}
		
	
	
	
	
	
	
	
	
}