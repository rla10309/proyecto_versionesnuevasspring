package com.dawes.modelo;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "grupos", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class GrupoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idgrupo;
	@Column(length = 50, nullable = false)
	private String nombre;
	@Column(nullable = true)
	private int miembros;
	private String imagen;
	@Column(length = 20)
	private String estilo;
	@Column(length = 30)
	private String origen;
	@Column(columnDefinition = "TEXT")
	private String descripcion;
	@Column(columnDefinition = "TEXT")
	private String subtexto;

	@OneToMany(mappedBy = "grupo")
	private List<ConciertoVO> conciertos;

	public GrupoVO(String nombre, int miembros, String imagen, String estilo, String origen, String descripcion,
			String subtexto) {
		super();
		this.nombre = nombre;
		this.miembros = miembros>0?miembros:0;
		this.imagen = imagen;
		this.estilo = estilo;
		this.origen = origen;
		this.descripcion = descripcion;
		this.subtexto = subtexto;
	}

	@Override
	public String toString() {
		return "GrupoVO [idgrupo=" + idgrupo + ", nombre=" + nombre + ", miembros=" + miembros + ", imagen=" + imagen
				+ ", estilo=" + estilo + ", origen=" + origen + ", descripcion=" + descripcion + "]";
	}

}
