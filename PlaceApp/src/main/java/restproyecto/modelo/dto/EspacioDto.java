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

    private String nombreEspacio;
    private String descripcion;
    private int cp;
    private String provincia;
    private String subtipo;
    private double precio;
    private List<ExtraDto> extras;
}