package restproyecto.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproyecto.modelo.dto.EspacioClienteDto;
import restproyecto.modelo.dto.ReservaUsuarioClienteDto;
import restproyecto.modelo.entities.Espacio;
import restproyecto.modelo.entities.Reserva;
import restproyecto.modelo.entities.Subtipo;
import restproyecto.service.EspacioService;
import restproyecto.service.ExtraService;
import restproyecto.service.PerfilService;
import restproyecto.service.ProvinciaService;
import restproyecto.service.ReservaService;
import restproyecto.service.SubtipoService;
import restproyecto.service.TipoService;
import restproyecto.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/cliente")
public class ClienteRestController {
	@Autowired
	private TipoService tipoService;

	@Autowired
	private SubtipoService subtipoService;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private ReservaService reservaService;
	
	//Mostrar espacios buscando los espacios por id de subtipo y provincia
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
		
	
	///Mostrar las reservas asociadas al usuario asignado
		@GetMapping("/reserva/{username}")
		public List<ReservaUsuarioClienteDto> buscarReservaPorUsuario(@PathVariable String username){
			List<Reserva> reservas = reservaService.obtenerReservaPorUsuario(username);
			List<ReservaUsuarioClienteDto> reservasDto = new ArrayList<>();
			List<Reserva> reservasEnFecha = new ArrayList<>();
			LocalDateTime fechaActual = LocalDateTime.now();
			
			for (Reserva r :reservas) {
				
				LocalDateTime fechaFintmp = r.getFechaFin().toLocalDateTime(); 
				
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
		
		
	}
	

	    		
	
	
	
	
	
	

