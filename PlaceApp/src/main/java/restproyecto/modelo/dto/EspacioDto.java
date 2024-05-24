package restproyecto.modelo.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EspacioDto {

	private int idEspacio;
    private String nombreEspacio;
    private String descripcion;
    private int cp;
    private String direccion;
    private String provincia;
    private String subtipo;
    private double precio;
    private String username;
    private String nombreUsername;
    private int telefonoUsername;
    private String correoUsername;
    
    private List<ExtraDto> extras;
}
