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

/**
	 * The persistent class for the eventos database table.
	 * 
	 */
	@Entity
	@Table(name="espacios")
	@NamedQuery(name="Espacio.findAll", query="SELECT e FROM Espacio e")
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
		
		private BigDecimal precio;
		
		//uni-directional many-to-one association to provincias
		@ManyToOne
		@JoinColumn(name="ID_PROV")
		private Provincia provincia;

		

		public Espacio() {
		}

		public int getIdEspacio() {
			return this.idEspacio;
		}

		public void setIdEvento(int idEspacio) {
			this.idEspacio = idEspacio;
		}
		
		public String getNombre() {
			return this.nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getDescripcion() {
			return this.descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getDireccion() {
			return this.direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
	
		public int getCp() {
			return this.cp;
		}

		public void setCp(int cp) {
			this.cp = cp;
		}

		

		public int getEnabled() {
			return this.enabled;
		}

		public void setEnabled(int enabled) {
			this.enabled = enabled;
		}

		public BigDecimal getPrecio() {
			return this.precio;
		}

		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}

		
		
		public Provincia getProvincia() {
			return this.provincia;
		}

		public void setProvincia(Provincia provincia) {
			this.provincia = provincia;
		}

}
