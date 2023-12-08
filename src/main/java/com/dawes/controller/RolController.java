package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioRol;

@Controller
@RequestMapping("/rol")
public class RolController {
	@Autowired
	ServicioRol sr;

	@RequestMapping("/listadoroles")
	public String listadoroles(Model modelo) {
		modelo.addAttribute("roles", sr.findAll());
		return "admin/rol/listadoroles";
	}

	@RequestMapping("/nuevorol")
	public String nuevorol(Model modelo) {
		modelo.addAttribute("rol", new RolVO());
		modelo.addAttribute("mensaje", "Recuerda introducir 'ROLE_' al darle nombre al rol");
		return "admin/rol/forminsertar";
	}

	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute RolVO rol, Model modelo) {
		try {
			sr.save(rol);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("rol", new RolVO());
			modelo.addAttribute("mensaje", "Ya existe un rol con ese nombre");
			return "admin/rol/forminsertar";
		}
		modelo.addAttribute("roles", sr.findAll());
		modelo.addAttribute("mensaje", "La seguridad está configurada para otros roles");
		return "admin/rol/listadoroles";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int idrol, Model modelo) {
		modelo.addAttribute("rol", sr.findById(idrol).get());
		return "admin/rol/forminsertar";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute RolVO rol, Model modelo) {

		try {
			if (!sr.findById(rol.getIdrol()).get().getNombre().equals("ROLE_ADMIN")) {
				sr.delete(rol);
				modelo.addAttribute("msgSuccess", "Rol eliminado con éxito");
			} else {
				modelo.addAttribute("msgError", "El rol de Administrador no se puede borrar");
			}

		} catch (Exception e) {
			modelo.addAttribute("msgError", "Este rol está siendo utilizado");
		}

		modelo.addAttribute("roles", sr.findAll());

		return "admin/rol/listadoroles";

	}

}
