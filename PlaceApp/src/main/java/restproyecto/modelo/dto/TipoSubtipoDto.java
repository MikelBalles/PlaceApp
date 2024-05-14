package restproyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TipoSubtipoDto {
	private int idTipo;
	private String nombreTipo;
	private int idSubtipo;
	private String NombreSubtipo;
	
}
