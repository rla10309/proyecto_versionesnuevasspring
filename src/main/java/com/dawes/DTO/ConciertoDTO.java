package com.dawes.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConciertoDTO {
	private int idconcierto;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hora;
	private double preciotaquilla;
	private double precioanticipado;
	private int plazas;
	private int idgrupo;

}
