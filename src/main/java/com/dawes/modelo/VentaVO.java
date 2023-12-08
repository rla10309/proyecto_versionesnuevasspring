package com.dawes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@Entity
@Table(name = "ventas", uniqueConstraints = @UniqueConstraint(columnNames = { "fechaventa", "horaventa" }))
public class VentaVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idventa;
	private int numeroentradas;
	private LocalDate fechaventa;
	private LocalTime horaventa;

	@ManyToOne
	@JoinColumn(name = "idusuario")
	private UsuarioVO usuario;

	@ManyToOne
	@JoinColumn(name = "idconcierto")
	private ConciertoVO concierto;

	public VentaVO(int numeroentradas, UsuarioVO usuario, ConciertoVO concierto) {
		super();
		this.numeroentradas = numeroentradas;
		this.fechaventa = LocalDate.now();
		this.horaventa = LocalTime.now();
		this.usuario = usuario;
		this.concierto = concierto;
	}

	public VentaVO() {
		super();
		this.fechaventa = LocalDate.now();
		this.horaventa = LocalTime.now();
	}

	public VentaVO(int numeroentradas, LocalDate fechaventa, LocalTime horaventa, UsuarioVO usuario,
			ConciertoVO concierto) {
		super();
		this.numeroentradas = numeroentradas;
		this.fechaventa = fechaventa;
		this.horaventa = horaventa;
		this.usuario = usuario;
		this.concierto = concierto;
	}

}
