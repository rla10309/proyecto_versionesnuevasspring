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
import com.dawes.modelo.GrupoVO;
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
		if (authentication != null) {
			UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
			modelo.addAttribute("usuario", usuario);
		}
		// modelo.addAttribute("conciertos", sc.findAll());
		modelo.addAttribute("grupos", sg.findAll());
		return "index";
	}

	@RequestMapping("public/findgrupobyid")
	public String findGrupoById(@RequestParam int idgrupo, Model modelo) {
		Optional<GrupoVO> grupo = sg.findById(idgrupo);
		Optional<List<ConciertoVO>> conciertos = sc.findByGrupoNombreIgnoreCase(grupo.get().getNombre());

		modelo.addAttribute("grupo", grupo.get());
		modelo.addAttribute("conciertos", conciertos.get());
		return "public/vistaconcierto";
	}

	@RequestMapping("/registrousuario")
	public String formRegistro(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "public/formregistro";
	}

	@RequestMapping("/registro")
	public String registrar(@ModelAttribute UsuarioVO usuario, Model modelo) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setRol(sr.findByNombre("ROLE_USER").get());
		try {
			su.save(usuario);
			modelo.addAttribute("msgSuccess", "Se ha registrado con éxito");
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("usuario", new UsuarioVO());
			modelo.addAttribute("msgError", "DNI o Email ya existen en el sistema");
			return "public/formregistro";
		}
		return "login";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
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
		
		GrupoVO grupo = sg.findByNombreIgnoreCase(nombre).get();
		
		List<ConciertoVO> conciertos = sc.findByGrupoNombreIgnoreCase(grupo.getNombre()).get();
		modelo.addAttribute("grupo", grupo);
		if (conciertos.size() > 0) {
			
			modelo.addAttribute("conciertos", conciertos);
		} 
		else {
			modelo.addAttribute("error", "Concert not found");
			
		}
		return "public/vistaconcierto";
	}

}
