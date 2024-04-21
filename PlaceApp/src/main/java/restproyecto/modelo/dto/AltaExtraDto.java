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
public class AltaExtraDto {
	 private String nombre;
	 private String descripcion;
	 private double precio;
}
