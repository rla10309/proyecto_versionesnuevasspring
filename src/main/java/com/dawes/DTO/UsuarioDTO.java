package com.dawes.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

	private int idUsuario;
	private String dni;
	private String password;

}
