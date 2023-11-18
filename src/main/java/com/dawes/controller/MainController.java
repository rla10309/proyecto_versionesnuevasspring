package com.dawes.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioVenta;

@Controller

public class MainController {
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioUsuario su;
	@Autowired
	ServicioGrupo sg;
	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioRol sr;
	@Autowired
	BCryptPasswordEncoder encoder;

	
	


	@RequestMapping({ "/", "/index" })
	public String index(Model modelo, Authentication authentication) {
		if(authentication != null) {
			UsuarioVO usuario =  (UsuarioVO) authentication.getPrincipal();
			modelo.addAttribute("usuario", usuario);
		}	
		modelo.addAttribute("conciertos", sc.findAll());
		return "index";
	}

	@RequestMapping("public/findgrupobyid")
	public String findGrupoById(@RequestParam int idgrupo, Model modelo) {
		modelo.addAttribute("grupo", sg.findById(idgrupo).get());
		return "public/vistaconcierto";
	}

	@RequestMapping("/registrousuario")
	public String formRegistro(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "public/formregistro";
	}

	@RequestMapping("/registro")
	public String registrar(@ModelAttribute UsuarioVO usuario) {
		 usuario.setPassword(encoder.encode(usuario.getPassword()));
		 usuario.setRol(sr.findByNombre("ROLE_USER").get());
		 su.save(usuario);
		 return "public/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "public/login";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin/admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "user/user";
	}


	@RequestMapping("/public/buscarporgrupo")

	public String findByGrupo(@RequestParam String nombre, Model modelo) {
		Optional<List<ConciertoVO>> conciertos = sc.findByGrupoNombre(nombre);

		if (conciertos.isPresent()) {
			modelo.addAttribute("conciertos", conciertos.get());
		} else {
			modelo.addAttribute("error", "Concert not found");
			return "error/errorPage"; 
		}
		return "index";
	}

}
