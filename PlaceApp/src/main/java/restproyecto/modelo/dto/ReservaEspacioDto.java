package restproyecto.modelo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restproyecto.modelo.entities.Provincia;
import restproyecto.modelo.entities.Usuario;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ReservaEspacioDto {
	
	    private String nombreEspacio;
	    
	    private Date fechaInicioReserva;
	    
	    private Date fechaFinReserva;
	    
	    private double precioVentaReserva;

}
