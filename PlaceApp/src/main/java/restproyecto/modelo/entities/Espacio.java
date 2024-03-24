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

		@Column(name="AFORO_MAXIMO")
		private int aforoMaximo;

		private String descripcion;

		private String destacado;

		private String direccion;

		private int duracion;

		private String estado;

		@Temporal(TemporalType.DATE)
		@Column(name="FECHA_INICIO")
		private Date fechaInicio;

		private String nombre;

		private BigDecimal precio;

		//uni-directional many-to-one association to Tipo
		@ManyToOne
		@JoinColumn(name="ID_TIPO")
		private Tipo tipo;

		public Espacio() {
		}

		public int getIdEspacio() {
			return this.idEspacio;
		}

		public void setIdEvento(int idEspacio) {
			this.idEspacio = idEspacio;
		}

		public int getAforoMaximo() {
			return this.aforoMaximo;
		}

		public void setAforoMaximo(int aforoMaximo) {
			this.aforoMaximo = aforoMaximo;
		}

		public String getDescripcion() {
			return this.descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDestacado() {
			return this.destacado;
		}

		public void setDestacado(String destacado) {
			this.destacado = destacado;
		}

		public String getDireccion() {
			return this.direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public int getDuracion() {
			return this.duracion;
		}

		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}

		public String getEstado() {
			return this.estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Date getFechaInicio() {
			return this.fechaInicio;
		}

		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}


		public String getNombre() {
			return this.nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public BigDecimal getPrecio() {
			return this.precio;
		}

		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}

		public Tipo getTipo() {
			return this.tipo;
		}

		public void setTipo(Tipo tipo) {
			this.tipo = tipo;
		}

}
