package restproyecto.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="reservas")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RESERVA")
	private int idReserva;
	
	@Column(name="FECHA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaInicio;
	
	@Column(name="FECHA_FIN")
	@Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaFin;
	
	private int enabled;

	private String observaciones;

	@Column(name="PRECIO_VENTA")
	private double precioVenta;

	@ManyToOne
	@JoinColumn(name="ID_ESPACIO")
	private Espacio espacio;

	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Usuario usuario;

	
}


