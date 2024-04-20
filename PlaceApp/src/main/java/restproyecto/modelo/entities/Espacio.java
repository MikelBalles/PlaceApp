package restproyecto.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
	 * The persistent class for the eventos database table.
	 * 
	 */
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	@Entity
	@Table(name="espacios")
	public class Espacio implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ID_ESPACIO")
		private int idEspacio;
		
		private String nombre;
		
		private String descripcion;
		
		private String direccion;
		
		private int cp;
		
		private int enabled;
		
		private double precio;
		
		@ManyToOne
		@JoinColumn(name="ID_PROV")
		private Provincia provincia;
		
		@ManyToOne
		@JoinColumn(name="ID_USUARIO")
		private Usuario usuario;
		
		@ManyToOne
		@JoinColumn(name="ID_SUBTIPO")
		private Subtipo subtipo;

}
