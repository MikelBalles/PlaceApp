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

public class ReservaUsuarioDto {
	private int idReserva;
	private String nombreEspacio;
	private String descripcionEspacio;
	private String direccionEspacio;
	private String nombreUsuario;
	private int telefonoUsuario;
	private String direccionUsuario;
	private Timestamp fechaInicioReserva;
	private Timestamp fechaFinReserva;
	private double precioVenta;
	private String observacionReserva;
	private String nombreUsurnameReserva;
	private int tlfUsernameResrva;
	private String direccionUsernameReserva;
	
	
	

}
