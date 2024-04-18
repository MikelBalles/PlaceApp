package restproyecto.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the tipos database table.
 * 
 */
@Entity
@Table(name="subtipos")
@NamedQuery(name="Subtipo.findAll", query="SELECT s FROM Subtipo s")
public class Subtipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUBTIPO")
	private int idSubtipo;

	private String nombre;
	
	//uni-directional many-to-one association to Subtipo
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private Tipo tipo;

	public Subtipo() {
	}

	public int getIdSubtipo() {
		return this.idSubtipo;
	}

	public void setIdSubtipo(int idSubtipo) {
		this.idSubtipo = idSubtipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}



}

