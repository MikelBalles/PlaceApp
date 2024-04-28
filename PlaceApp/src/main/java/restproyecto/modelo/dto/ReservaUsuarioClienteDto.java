package restproyecto.modelo.dto;

import java.sql.Date;

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
	private Date fechaInicio;
	private Date fechaFin;
	private double precioVenta;
	
	
	
	
}
