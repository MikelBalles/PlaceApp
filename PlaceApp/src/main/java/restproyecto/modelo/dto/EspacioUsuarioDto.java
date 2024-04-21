package restproyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EspacioUsuarioDto {
	private int idEspacio;
    private String nombre;
    private String descripcion;
    private String nombreTipoEspacio;
    private double precioPorHora;
}
