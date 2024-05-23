package restproyecto.restcontroller;

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
import restproyecto.modelo.dto.UsuarioDatosDto;
import restproyecto.modelo.dto.UsuarioPerfilDto;
import restproyecto.modelo.entities.Perfil;
import restproyecto.modelo.entities.Usuario;
import restproyecto.service.PerfilService;
import restproyecto.service.UsuarioService;

/**
 * Controlador REST para las operaciones relacionadas con la autorizacion y la modificacion de los perfiles de los usuarios de la aplicacion.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/")



public class UserManagementRestController {

	/**
     * Servicio para gestionar la información de los usuarios.
     */
	@Autowired
	private UsuarioService usuarioService;
	
	/**
     * Servicio para gestionar la información de los perfiles de usuario.
     */
	@Autowired
	private PerfilService perfilService;
	
	
	
	/**
     * Peticion que inicia la sesión de usuario y devuelve los detalles del perfil.
     *
     * @param usuario Objeto Usuario con credenciales de inicio de sesión.
     * @return Detalles del perfil del usuario autenticado.
     */
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
	
	    /**
	     * Consulta a la base de datos que obtiene todos los perfiles disponibles.
	     *
	     * @return Lista de todos los perfiles.
	     */
	 @GetMapping("/perfiles")
		public List <Perfil> todosPerfiles (){
			return perfilService.buscarPerfiles();
			
	 
	 }
	 
	 /**
	     *Consutla a la base de datos que obtiene los datos personales de un usuario.
	     *
	     * @param idUsuario Identificador de usuario.
	     * @return Datos personales del usuario.
	     */
	 
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
	 	/**
	     * Modificacion a la base de datos que actualiza los datos de un usuario.
	     *
	     * @param actualizarUsuarioDto DTO con los nuevos datos del usuario.
	     * @return Respuesta de la actualización. En caso de una actualización exitosa, 
	     * devuelve un mensaje indicando que la actualización se realizó correctamente. 
	     * 
	     * @return En caso de error, devuelve un mensaje indicando que hubo un problema durante la actualización.
	     */
	     
	 @PutMapping("/actualizarUsuario")
	 public ResponseEntity<?> actualizarUsuario(@RequestBody ActualizarUsuarioDto actualizarUsuarioDto) {
	   
	     // Crear un objeto Usuario con los datos proporcionados por el DTO
	     Usuario usuario = usuarioService.obtenerUsuarioPorId(actualizarUsuarioDto.getUsername());
	    
	     usuario.setNombre(actualizarUsuarioDto.getNombre());
	     usuario.setApellidos(actualizarUsuarioDto.getApellidos());
	     usuario.setDireccion(actualizarUsuarioDto.getDireccion());
	     usuario.setTelefono(actualizarUsuarioDto.getTelefono());

	     // Verificar si se proporcionó una nueva contraseña
	     if (actualizarUsuarioDto.getPassword() != null && !actualizarUsuarioDto.getPassword().isEmpty()) {
	         usuario.setPassword(actualizarUsuarioDto.getPassword());
	     }

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


	 
	 

	


