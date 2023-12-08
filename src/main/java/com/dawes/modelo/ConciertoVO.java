package com.dawes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conciertos", uniqueConstraints = @UniqueConstraint(columnNames = { "fecha", "hora" }))
public class ConciertoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idconcierto;
	@Column(nullable = false)
	private double precioanticipado;
	@Column(nullable = false)
	private double preciotaquilla;
	@Column(nullable = false)
	private LocalDate fecha;
	@Column(nullable = false)
	private LocalTime hora;
	@Column(nullable = false)
	private int plazas;
	@Column(columnDefinition = "TEXT", nullable = true)
	private String intro;

	@ManyToOne
	@JoinColumn(name = "idgrupo")
	private GrupoVO grupo;

	@OneToMany(mappedBy = "concierto")
	private List<VentaVO> ventas;

	public ConciertoVO(double precioanticipado, double preciotaquilla, LocalDate fecha, LocalTime hora, int plazas,
			GrupoVO grupo) {
		super();
		this.precioanticipado = precioanticipado;
		this.preciotaquilla = preciotaquilla;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "ConciertoVO [idconcierto=" + idconcierto + ", precioanticipado=" + precioanticipado
				+ ", preciotaquilla=" + preciotaquilla + ", fecha=" + fecha + ", hora=" + hora + ", plazas=" + plazas
				+ ", grupo=" + grupo.getNombre() + "]";
	}

}
