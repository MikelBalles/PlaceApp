package restproyecto.restcontroller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.ActualizarUsuarioDto;
import restproyecto.modelo.dto.AltaEspacioDto;
import restproyecto.modelo.dto.AltaExtraDto;
import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.EspacioUsuarioDto;
import restproyecto.modelo.dto.ExtraDto;
import restproyecto.modelo.dto.ReservaEspacioDto;
import restproyecto.modelo.dto.ReservaUsuarioDto;
import restproyecto.modelo.dto.SubtipoEspacioDto;
import restproyecto.modelo.dto.UsuarioDatosDto;
import restproyecto.modelo.dto.UsuarioPerfilDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Perfil;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.modelo.entities.Usuario;
import restproyecto.service.EspacioService;
import restproyecto.service.ExtraService;
import restproyecto.service.PerfilService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
import restproyecto.service.UsuarioService;


/**
 * Controlador REST para las operaciones relacionadas con las reservas y gestión de espacios por parte de los propietarios.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/propietario")

public class PropietarioRestController {
	 /**
     * Servicio para gestionar la información de las reservas.
     */
	@Autowired
	private ReservaService reservaService;
	
	/**
     * Servicio para gestionar la información de los usuarios.
     */
	@Autowired
	private UsuarioService usuarioService;
	
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
     * Servicio para gestionar la información de los espacios.
     */
	@Autowired
	private EspacioService espacioService;
	
	/**
     * Servicio para gestionar la información de los extras asociados a un espacio.
     */
	@Autowired
	private ExtraService extraService;
	
<<<<<<< HEAD
=======
	/**
     * Servicio para gestionar la información de los perfiles de usuario.
     */
	@Autowired
	private PerfilService perfilService;
	
	
	
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
	public ResponseEntity<?> obtenerReservasPorEspacio(@PathVariable int idEspacio) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas = reservaService.obtenerReservaPorEspacio(idEspacio);
		if (reservas != null) {
			List<ReservaEspacioDto> listDto = new ArrayList<ReservaEspacioDto>();
			for (Reserva r : reservas) {
				ReservaEspacioDto resDto = new ReservaEspacioDto();
				resDto.setFechaInicioReserva(r.getFechaInicio());
				resDto.setFechaFinReserva(r.getFechaFin());
				listDto.add(resDto);
			}
	        return ResponseEntity.status(HttpStatus.OK).body(listDto);

		}
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen reservas asociadas a ese espacio");
>>>>>>> df0ef95521d35f2e973ebd54464dca10cbc0c18f

	


	/**
     * Peticion a la base de datos que crea un nuevo espacio.
     *
     * @param bodyDto DTO con la información del espacio a crear.
     * @return El nuevo espacio creado en la base de datos.
     */
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
	
	
	/**
     * Consutla en la base de datos que obtiene los espacios asociados a un usuario.
     *
     * @param username identificativo de un usuario (id usuario).
     * @return Lista de espacios asociados al usuario.
     */
	@GetMapping("/espacio/usuario/{username}")
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
	
	
	/**
     * Consulta a la base de datos que obtiene los detalles indicados en el Dto de un espacio.
     *
     * @param idEspacio Identificador del espacio.
     * @return Detalles del espacio, exclusivamente los detalles del Dto.
     */
	@GetMapping("/espacio/{idEspacio}")
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
	
	  /**
     * Consutla el la base de datos que obtiene los detalles de una reserva asociada a un espacio.
     *
     * @param idReserva Identificador de la reserva.
     * @return Detalles de la reserva.
     */

	@GetMapping("/reserva/usuario/{idReserva}")
	public ReservaUsuarioDto obtenerReservaUsuario(@PathVariable int idReserva) {
		Reserva r = reservaService.buscarPorId(idReserva);
		ReservaUsuarioDto rDto = new ReservaUsuarioDto();
			
			rDto.setIdReserva(r.getIdReserva());
			rDto.setNombreEspacio(r.getEspacio().getNombre());
			rDto.setDescripcionEspacio(r.getEspacio().getDescripcion());
			rDto.setDireccionEspacio(r.getEspacio().getDireccion());
			rDto.setNombreUsuario(r.getEspacio().getUsuario().getNombre());
			rDto.setTelefonoUsuario(r.getEspacio().getUsuario().getTelefono());
			rDto.setDireccionUsuario(r.getEspacio().getUsuario().getDireccion());
			rDto.setFechaInicioReserva(r.getFechaInicio());
			rDto.setFechaFinReserva(r.getFechaFin());
			rDto.setPrecioVenta(r.getPrecioVenta());
			rDto.setObservacionReserva(r.getObservaciones());
			rDto.setNombreUsurnameReserva(r.getUsuario().getUsername());
			rDto.setTlfUsernameResrva(r.getUsuario().getTelefono());
			rDto.setDireccionUsernameReserva(r.getUsuario().getDireccion());
			
		return rDto;
	}
		
	/**
	 * Recupera la reserva que se corresponde con el ID que llega, y establece
	 * el campo ENABLED a 0. 
	 * 
	 * @param id El id de la reserva
	 * @return Devuelve un status y mensaje personalizado para cada caso.
	 */
	@PutMapping("/reserva/cancelarReserva/{id}")
	public ResponseEntity<?> cancelarReserva(@PathVariable int id) {
		Reserva reserva = reservaService.buscarPorId(id);
		if (reserva != null) {
			reserva.setEnabled(0);
			if (reservaService.modificarReserva(reserva)) {
				return ResponseEntity.status(HttpStatus.OK).body("Reserva cancelada");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reserva NO cancelada");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID reserva NO existe");

	}

}
