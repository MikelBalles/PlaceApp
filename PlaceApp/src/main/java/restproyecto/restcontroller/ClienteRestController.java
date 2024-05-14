package restproyecto.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.AltaEspacioDto;
import restproyecto.modelo.dto.AltaExtraDto;
import restproyecto.modelo.dto.EspacioClienteDto;
import restproyecto.modelo.dto.EspacioDto;
import restproyecto.modelo.dto.ExtraDto;
import restproyecto.modelo.dto.ReservaUsuarioClienteDto;
import restproyecto.modelo.dto.ReservaUsuarioDto;
import restproyecto.modelo.dto.TipoSubtipoDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Extra;
import restproyecto.modelo.entities.Perfil;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.modelo.entities.Usuario;
import restproyecto.service.EspacioService;
import restproyecto.service.ExtraService;
import restproyecto.service.PerfilService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
import restproyecto.service.TipoService;
import restproyecto.service.UsuarioService;
/*
 * Controlador Rest para las opciones relacionadas con los clientes de la aplicacion
 */
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/cliente")
public class ClienteRestController {


	/**
     * Servicio para gestionar la información de los espacios.
     */
	@Autowired
	private EspacioService espacioService;
	
	/**
     * Servicio para gestionar la información de las reservas.
     */
	@Autowired
	private ReservaService reservaService;
	
	/**
     * Servicio para gestionar la información de los extras asociados a cada reserva.
     */
	@Autowired
	private ExtraService extraService;
	
	/**
     * Servicio para gestionar la información de los usuarios.
     */
	@Autowired
	private UsuarioService usuarioService;

	/**
     * Servicio para gestionar la información de subtipos.
     */
	@Autowired
	private SubtipoService subtipoService;
	
	
	
	
	
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
     * Consulta a la base de datos que obtiene las reservas asociadas a un usuario.
     *
     * @param username identificativo del usuario(id Usuario String).
     * @return Lista de reservas del usuario.
     */
		@GetMapping("/reserva/{username}")
		public List<ReservaUsuarioClienteDto> buscarReservaPorUsuario(@PathVariable String username){
			List<Reserva> reservas = reservaService.obtenerReservaPorUsuario(username);
			List<ReservaUsuarioClienteDto> reservasDto = new ArrayList<>();
			List<Reserva> reservasEnFecha = new ArrayList<>();
			LocalDateTime fechaActual = LocalDateTime.now();
			
			for (Reserva r :reservas) {
				
				LocalDateTime fechaFintmp = r.getFechaFin().toLocalDateTime(); 
				System.out.println(fechaActual);
				System.out.println(fechaFintmp);
				if (fechaFintmp.isBefore(fechaActual)) {
						continue;
				}
				
				
				ReservaUsuarioClienteDto ruDto = new ReservaUsuarioClienteDto();
				ruDto.setIdReserva(r.getIdReserva());
				ruDto.setNombreEspacio(r.getEspacio().getNombre());
				ruDto.setObservacionesReserva(r.getObservaciones());
				ruDto.setFechaInicio(r.getFechaInicio());
				ruDto.setFechaFin(r.getFechaFin());
				ruDto.setPrecioVenta(r.getPrecioVenta());
				reservasDto.add(ruDto);
			}
			
			return reservasDto;
		}
		
		
		/**
	     * Consulta a la base de datos que obtiene los detalles de un espacio introducidos en un Dto.
	     *
	     * @param idEspacio Identificador del espacio.
	     * @return Detalles del espacio exclusivamente los datos introducidos en Dto.
	     */
	

		@GetMapping("espacio/cliente/{idEspacio}")
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
				dto.setDescripcion(extra.getDescripcion());
		
				listaExtraDto.add(dto);
	}
	
			//Construimos el Dto a devolver
			eDto.setIdEspacio(espacio.getIdEspacio());
			eDto.setNombreEspacio(espacio.getNombre());
			eDto.setDescripcion(espacio.getDescripcion());
			eDto.setCp(espacio.getCp());
			eDto.setPrecio(espacio.getPrecio());
			eDto.setProvincia(espacio.getProvincia().getNombre());
			eDto.setNombreUsername(espacio.getUsuario().getNombre());
			eDto.setTelefonoUsername(espacio.getUsuario().getTelefono());
			
			eDto.setExtras(listaExtraDto);
			
			
	
			return eDto;
}
		
		 /**
	     * Consulta a la base de datos que obtiene todas las reservas.
	     *
	     * @return Lista de todas las reservas.
	     */
		
		@GetMapping ("/reserva/todas/")
		public List<Reserva> todasReservas(){
			return reservaService.buscarTodas();
			
			
			
		}
	
		/**
	     * Peticion a la base de datos que crea una nueva reserva en un espacio existente para un usuario.
	     *
	     * @param bodyDto DTO con la información de la reserva.
	     * @return Crea una nueva reserva para un usuario en la base de datos.
	     */
		
		@PostMapping("/espacio/altaReserva")
		public ResponseEntity<?> reservarEspacio(@RequestBody ReservaUsuarioDto bodyDto) {
		    // Comprobamos si el usuario tiene el rol de cliente
		    Usuario usu = usuarioService.obtenerUsuarioPorId(bodyDto.getUsernameReserva());
		    if (usu == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
		    }
		    
		    Perfil perfil = usu.getPerfil();
		    if (perfil.getIdPerfil() != 3) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil incorrecto");
		    }

		    // Verificar si el espacio existe
		    Espacio espacio = espacioService.buscarPorId(bodyDto.getIdEspacio());
		    if (espacio == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El espacio no existe");
		    }

		    // Construir la reserva para dar de alta
		    Reserva reserva = new Reserva();
		    reserva.setEspacio(espacio);
		    reserva.setUsuario(usu);
		    reserva.setPrecioVenta(bodyDto.getPrecioVenta());
		    reserva.setFechaInicio(bodyDto.getFechaInicioReserva());
		    reserva.setFechaFin(bodyDto.getFechaFinReserva());
		    reserva.setEnabled(1);
		    reserva.setObservaciones(bodyDto.getObservacionReserva());

		    // Dar de alta la reserva en BBDD
		    Reserva reservaAlta = reservaService.altaReserva(reserva);

		    if (reservaAlta == null) {
		        return ResponseEntity.badRequest().build();
		    }

		    return ResponseEntity.ok(reservaAlta);
		}
		
		
		@GetMapping("/tipo/subtipo")
		public List<TipoSubtipoDto> getTipoySubtipo() {
			List <Subtipo> listaSubtipo = new ArrayList<>();
			listaSubtipo = subtipoService.buscarSubtipo();
			List <TipoSubtipoDto> listaDto = new ArrayList<>();
			for (Subtipo s:listaSubtipo ) {
				TipoSubtipoDto tsDto = new TipoSubtipoDto();
				tsDto.setIdSubtipo(s.getIdSubtipo());
				tsDto.setNombreSubtipo(s.getNombre());
				tsDto.setIdTipo(s.getTipo().getIdTipo());
				tsDto.setNombreTipo(s.getTipo().getNombre());
				
				listaDto.add(tsDto);
			}
			return listaDto;
		}
		
		
		

}



