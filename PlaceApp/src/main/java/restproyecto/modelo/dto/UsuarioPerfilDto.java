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
public class UsuarioPerfilDto {
	private String username;
	private String nombreUsuario;
	private int idPerfil;
	private String nombrePerfil;
	

}
