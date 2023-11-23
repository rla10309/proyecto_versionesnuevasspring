package com.dawes.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.DTO.UsuarioDTO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioUsuario;

@RestController
@RequestMapping("/api")
public class UsuarioWS {
	
	@Autowired
	ServicioUsuario su;
	

	
	
	/*
	 * Muestra la contraseña codificada de un usuario
	 */
	
	@GetMapping("/usuario/{dni}")
	public ResponseEntity<?> findByUsuarioDni(@PathVariable String dni){
		UsuarioVO usuario = su.findByDni(dni).get();
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setIdUsuario(usuario.getIdusuario());
		usuarioDto.setDni(usuario.getDni());
		usuarioDto.setPassword(usuario.getPassword());
		return new ResponseEntity<UsuarioDTO>(usuarioDto, HttpStatus.OK);
		
	}
	

}
