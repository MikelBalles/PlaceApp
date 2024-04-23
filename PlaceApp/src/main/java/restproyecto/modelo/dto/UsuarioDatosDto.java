package restproyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioDatosDto {

	private String nombre;
	private String apellidos;
	private String direccion;
	private int telefono;
	
	
}
