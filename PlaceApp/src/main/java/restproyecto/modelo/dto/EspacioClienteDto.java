package restproyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EspacioClienteDto {
	 private int idEspacio;
	    private String nombre;
	    private String descripcion;
	    private String direccion;
	    private double precio;
	    private int idSubtipo;
}
