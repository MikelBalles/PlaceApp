package restproyecto.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	private String username;
	
	private String password;

	private String nombre;
	

	private String apellidos;

	private String direccion;
	
	private int telefono;
	

	private int enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name="ID_PROV")
	private Provincia provincia;
	
	@ManyToOne
	@JoinColumn(name="ID_PERFIL")
	private Perfil perfil;


	
}
