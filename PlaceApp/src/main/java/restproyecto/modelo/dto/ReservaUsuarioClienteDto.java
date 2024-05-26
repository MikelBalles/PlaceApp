package restproyecto.modelo.dto;



import java.sql.Timestamp;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservaUsuarioClienteDto {

	private int idReserva;
	private String nombreEspacio;
	private String observacionesReserva;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private double precioVenta;
	private int idSubtipo;
	
	
	
	
}
