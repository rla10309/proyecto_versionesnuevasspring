package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.RolVO;

import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuario;

import com.dawes.servicio.ServicioVenta;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	ServicioUsuario su;
	
	@Autowired
	ServicioRol sr;

	
	@Autowired
	ServicioVenta sv;
	
	@RequestMapping("/listadousuarios")
	public String listadoUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", su.findAll());
		return "admin/usuario/listadousuarios";
		
	}
	
	@RequestMapping("/registrousuario")
	public String registroUsuario(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		modelo.addAttribute("roles", sr.findAll());
		return "admin/usuario/formregistro";
	}
	@RequestMapping("/insertausuario")
	public String insertausuario(@ModelAttribute UsuarioVO usuario, Model modelo) {
		
		try {
			su.save(usuario);
		}catch(Exception e) {
			modelo.addAttribute("Error", "Error en " + e.getStackTrace());
			return "redirect:/usuario/registrousuario?error=true";
		}
		return "redirect:/usuario/listadousuarios";
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam int idusuario, Model modelo) {
		modelo.addAttribute("usuario", su.findById(idusuario).get());
		modelo.addAttribute("roles", sr.findAll());
		return "admin/usuario/formregistro";
	}
//	
//	@RequestMapping("/insertaeditado")
//	public String insertaeditado(@ModelAttribute UsuarioRolVO usuariorol) {
//		UsuarioVO usuario = usuariorol.getUsuario();
//		su.save(usuario);
//		sur.save(usuariorol);
//		return "redirect:/usuario/listadousuarios";
//		
//	}
		
	@RequestMapping("/delete")
	public String delete(@RequestParam int idusuario) {
		try {
		su.deleteById(idusuario);
		}catch(DataIntegrityViolationException e) {
			return "redirect:/usuario/listadousuarios?error=true";
		}
		return "redirect:/usuario/listadousuarios?success=true";
	}
	
	@RequestMapping("/historial")
	public String findByDni(@RequestParam String dni, Model modelo) {
		modelo.addAttribute("ventas",sv.findByUsuarioDni(dni).get());
		modelo.addAttribute("usuario", su.findByDni(dni).get());
		return "admin/usuario/historialusuario";
	}
		
	
	
	
	

}
