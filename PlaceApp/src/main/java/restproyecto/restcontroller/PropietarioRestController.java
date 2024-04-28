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
	@Autowired
	private PerfilService perfilService;
	
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
/////Detalles de una reserva, muestra el nombre y detalles del espacio, nombre y detalle del usuario que ha realizado la reserva.

	@GetMapping("espacio/reserva/usuario/{idReserva}")
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
			
		

	
	
	////Validacion de usuario con username+password,una vez el usuario autenticado mostrar los detalles del usuario
	 @PostMapping("/iniciarSesion")
	    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario) {
	        Usuario usuarioAutenticado = usuarioService.login(usuario.getUsername(), usuario.getPassword());
	        if (usuarioAutenticado != null) {
	            // Construir un objeto DTO con la información requerida
	            UsuarioPerfilDto usuarioPerfilDto = new UsuarioPerfilDto();
	            usuarioPerfilDto.setUsername(usuarioAutenticado.getUsername());
	            usuarioPerfilDto.setNombreUsuario(usuarioAutenticado.getNombre());
	            usuarioPerfilDto.setIdPerfil(usuarioAutenticado.getPerfil().getIdPerfil());
	            usuarioPerfilDto.setNombrePerfil(usuarioAutenticado.getPerfil().getNombre());
	            return ResponseEntity.ok(usuarioPerfilDto);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credenciales inválidas");
	        }
	    }
	
/////mostrar todo los perfiles disponibles
	 @GetMapping("/perfiles")
		public List <Perfil> todosPerfiles (){
			return perfilService.buscarPerfiles();
			
	 
	 }
	 
	 ////Mostar usuarios con los datos del dto UsuarioDatosDto
	 
	 @GetMapping("/datosPropios/{idUsuario}")
	    public UsuarioDatosDto getDatosPropiosUsuario(@PathVariable String idUsuario) {
	      
	        Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);

	        return new UsuarioDatosDto(
	            usuario.getNombre(),
	            usuario.getApellidos(),
	            usuario.getDireccion(),
	            usuario.getTelefono()

	        );
}
	// Modificar los datos del usuario 
	 @PutMapping("/actualizarUsuario")
	 public ResponseEntity<?> actualizarUsuario(@RequestBody ActualizarUsuarioDto actualizarUsuarioDto) {
	   
	     System.out.println(actualizarUsuarioDto);

	     // Crear un objeto Usuario con los datos proporcionados por el DTO
	     Usuario usuario = new Usuario();
	     usuario.setNombre(actualizarUsuarioDto.getNombre());
	     usuario.setApellidos(actualizarUsuarioDto.getApellidos());
	     usuario.setDireccion(actualizarUsuarioDto.getDireccion());
	     usuario.setTelefono(actualizarUsuarioDto.getTelefono());

	     // Verificar si se proporcionó una nueva contraseña
	     if (actualizarUsuarioDto.getPassword() != null && !actualizarUsuarioDto.getPassword().isEmpty()) {
	         usuario.setPassword(actualizarUsuarioDto.getPassword());
	     }
	     System.out.println("Usuario a actualizar: " + usuario);

	     // Actualizar el usuario en la base de datos
	     boolean actualizacionExitosa = usuarioService.actualizarUsuario(usuario);

	     if (actualizacionExitosa) {
	         // Crear un nuevo DTO con los datos actualizados para devolver en la respuesta
	         UsuarioDatosDto usuarioActualizadoDto = new UsuarioDatosDto();
	         usuarioActualizadoDto.setNombre(usuario.getNombre());
	         usuarioActualizadoDto.setApellidos(usuario.getApellidos());
	         usuarioActualizadoDto.setDireccion(usuario.getDireccion());
	         usuarioActualizadoDto.setTelefono(usuario.getTelefono());

	         return ResponseEntity.ok(usuarioActualizadoDto);
	     } else {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el usuario");
	     }
	 }

	 }


	 
	 

	