package com.dawes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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

	@RequestMapping("/403")
	public String error() {
		return "403";
	}

	/*
	 * Aquí se maneja la búsqueda del grupo desde la caja que lo muestra, a través
	 * de su id
	 */
	@RequestMapping("public/findgrupobyid")
	public String findGrupoById(@RequestParam int idgrupo, Model modelo) {
		GrupoVO grupo = sg.findById(idgrupo).get();
		List<ConciertoVO> conciertos = sc.findByGrupoNombreIgnoreCase(grupo.getNombre()).get();

		modelo.addAttribute("grupo", grupo);
		if (conciertos.size() > 0) {
			modelo.addAttribute("conciertos", conciertos);
		} else {
			modelo.addAttribute("noconcert",
					"En estos momentos no hay conciertos programados de " + grupo.getNombre().toUpperCase());
		}
		modelo.addAttribute("now", LocalDate.now());
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
		return "login";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin/admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "user/user";
	}

	/* Esto maneja la búsqueda del grupo por su nombre en el formulario del index */
	@RequestMapping("/public/buscarporgrupo")
	public String findByGrupo(@RequestParam String nombre, Model modelo) {
		try {
			GrupoVO grupo = sg.findByNombreIgnoreCase(nombre).get();
			List<ConciertoVO> conciertos = sc.findByGrupoNombreIgnoreCase(grupo.getNombre()).get();
			modelo.addAttribute("grupo", grupo);
			if (conciertos.size() > 0) {
				modelo.addAttribute("conciertos", conciertos);
			} 
			return "public/vistaconcierto";
		} catch (NoSuchElementException e) {
			modelo.addAttribute("noelement",
					"No tenemos nada programado de " + nombre.toUpperCase() + " en estos momentos");
			modelo.addAttribute("grupos", sg.findAll());
			return "index";
		}

	}

}
