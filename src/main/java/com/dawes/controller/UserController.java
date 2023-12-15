package com.dawes.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioVenta;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioGrupo sg;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioUsuario su;

	@RequestMapping("/user")
	public String user(Authentication authentication, Model modelo) {
		UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
		List<VentaVO> ventas = sv.findByUsuarioDni(usuario.getDni()).get();
		int edad = LocalDate.now().getYear() - usuario.getFechanacimiento().getYear();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("edad", edad);
		if (!ventas.isEmpty())
			modelo.addAttribute("ventas", ventas);
		else {
			modelo.addAttribute("msgError", "No hay datos que mostrar");
		}

		return "user/user";
	}

	@RequestMapping("/userByDni")
	public String userByDni(@RequestParam String dni, Model modelo) {
		List<VentaVO> ventas = sv.findByUsuarioDni(dni).get();
		modelo.addAttribute("usuario", su.findByDni(dni).get());
		if (!ventas.isEmpty())
			modelo.addAttribute("ventas", ventas);
		else {
			modelo.addAttribute("msgError", "No hay datos que mostrar");
		}
		return "user/user";
	}

	@RequestMapping("/compra")
	public String compra(@RequestParam int idconcierto, Model modelo, Authentication authentication) {
		try {
			VentaVO venta = new VentaVO();
			ConciertoVO concierto = sc.findById(idconcierto).get();
			UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
			if (usuario != null) {
				venta.setConcierto(concierto);
				venta.setUsuario(usuario);
				modelo.addAttribute("venta", venta);
				modelo.addAttribute("grupo", concierto.getGrupo());
			}
		} catch (NullPointerException e) {
			modelo.addAttribute("msgError", "Debes estar registrado/a para comprar");
			return "login";
		}
		return "user/formcompra";

	}

	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute VentaVO venta, Model modelo) {

		try {
			
				sv.save(venta);
				modelo.addAttribute("grupo", sg.findById(venta.getConcierto().getGrupo().getIdgrupo()).get());
			
			
		} catch (Exception e) {
			System.out.println("pasa por aquí");
			modelo.addAttribute("msgError", "Espere unos segundos para seguir comprando");
			return "error";
		}
		return "user/ticket";
	}

	@RequestMapping("/findByFechaBetween")
	public String findByFechaBetween(@RequestParam LocalDate f_inicio, LocalDate f_fin, Model modelo,
			Authentication authentication) {
		UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
		List<ConciertoVO> conciertos = sc.findByFechaBetween(f_inicio, f_fin).get();
		boolean actual = false;

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (!conciertos.isEmpty()) {
			modelo.addAttribute("now", LocalDate.now());
			modelo.addAttribute("conciertos", conciertos);

		} else {

			modelo.addAttribute("msgError", "No hay conciertos entre esas fechas");
		}
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("fechas", "Conciertos entre " + formato.format(f_inicio) + " y " + formato.format(f_fin));
		return "user/user";

	}

	@RequestMapping("/darsedebaja")
	public String darsedebaja(Authentication authentication, Model modelo) {
		UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
		try {
			if (!usuario.getRol().getNombre().equals("ROLE_ADMIN")) {
				su.delete(usuario);
				modelo.addAttribute("mensaje", "Se ha eliminado su perfil correctamente");
			} else {
				modelo.addAttribute("usuario", usuario);
				modelo.addAttribute("mensaje", "Un administrador no puede darse de baja");
				return "user/user";
			}

		} catch (NoSuchElementException e) {
			return "error";

		}
		return "login";

	}
}
