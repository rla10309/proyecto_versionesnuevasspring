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
@Table(name="roles", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class RolVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrol;
	@Column(nullable = false, length = 20)
	private String nombre;
	
	@OneToMany(mappedBy = "rol")
	List<UsuarioVO> usuarios;
	
	public RolVO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
}



