package restproyecto.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="provincias")
@NamedQuery(name="Provincia.findAll", query="SELECT pr FROM Provincia pr")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L; 
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ID_PROV")
		private int idProv;

		private String nombre;
		


}