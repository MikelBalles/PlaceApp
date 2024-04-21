package restproyecto.modelo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AltaEspacioDto {

	private String username;
    private String nombreEspacio;
    private String descripcion;
    private String direccion;
    private int cp;
    private int idProv;
    private int idSubtipo;
    private double precio;
    private List<AltaExtraDto> extras;

}
