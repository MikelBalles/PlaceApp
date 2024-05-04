package restproyecto.modelo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExtraDto {
	 private int idExtra;
	 private String nombre;
	 private double precio;
	 private String descripcion;
	 
}
