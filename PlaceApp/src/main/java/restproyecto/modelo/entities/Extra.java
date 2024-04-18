package restproyecto.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="extras")
@NamedQuery(name="Extra.findAll", query="SELECT e FROM Extra e")
public class Extra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EXTRA")
	private int idExtra;
	
	private String nombre;
	
	private String descripcion;
	
	private BigDecimal precio;
	
	
	public Extra() {
	}

	public int getIdExtra() {
		return this.idExtra;
	}

	public void setIdExtra(int idExtra) {
		this.idExtra = idExtra;
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
	

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}
