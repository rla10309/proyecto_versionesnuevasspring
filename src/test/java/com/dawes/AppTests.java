package com.dawes;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.GrupoVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioVenta;

@SpringBootTest
class AppTests {

	@Autowired
	ServicioConcierto sc;

	@Autowired
	ServicioGrupo sg;

	@Autowired
	ServicioVenta sv;

	@Autowired
	ServicioUsuario su;

	@Autowired
	ServicioRol sr;

	// Crea grupos
	@Test
	public void test01() {
		sg.save(new GrupoVO("ABBA", 4, "imagenAbba.img", "Pop", "Suecia", "Waterlooooo", "Subtexto" ));
		sg.save(new GrupoVO("Queen", 4, "imagenQueen.img", "Rock", "UK", "Don't stop mi now", "Subtexto" ));
		assertNotNull(sg.save(new GrupoVO("The Police", 3, "imagenThePolice.img", "Rock", "UK", "so lonely","Subtexto")));
	}
//	
//	// Crea conciertos
	@Test
	public void test02() {
		sc.save(new ConciertoVO(55.0, 65.0, LocalDate.of(2024, 10, 12), LocalTime.of(20, 30), 300, sg.findByNombreIgnoreCase("ABBA").get()));
		sc.save(new ConciertoVO(40.0, 50.0, LocalDate.of(2024, 11, 20), LocalTime.of(20, 30), 300, sg.findByNombreIgnoreCase("Queen").get()));
		sc.save(new ConciertoVO(45.0, 55.0, LocalDate.of(2024, 11, 25), LocalTime.of(20, 30), 300, sg.findByNombreIgnoreCase("The Police").get()));
		assertNotNull(sc.save(new ConciertoVO(55.0, 65.0, LocalDate.of(2023, 10, 20), LocalTime.of(20, 30), 300, sg.findByNombreIgnoreCase("ABBA").get())));
	}

	// Buscar todos los conciertos de un grupo
	@Test
	public void test03() {
		List<ConciertoVO> conciertos = sc.findByGrupoNombreIgnoreCase("ABBA").get();
		assertEquals(2, conciertos.size());
	}

	// Elimina un grupo
	@Test
	public void test04() {
		
		try {
			sg.deleteById(sg.findByNombreIgnoreCase("ABBA").get().getIdgrupo());
		} catch(DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
		assertNotNull(sg.findByNombreIgnoreCase("ABBA").get());
	}

//	// Actualizamos un concierto 
	@Test
	public void test05() {
		ConciertoVO c = sc.findByFechaAndHora(LocalDate.of(2024, 11, 20), LocalTime.of(20, 30)).get();
		c.setPrecioanticipado(50.00);
		c.setPreciotaquilla(60.00);
		sc.save(c);
	}
//	
	// Insertamos roles
//	@Test
//	public void test06() {
//		sr.save(new RolVO("ROLE_ADMIN"));
//		assertNotNull(sr.save(new RolVO("ROLE_USER")));
//	}
//	
//	// Insertamos usuario
	@Test
	public void test07() {
		//try {
		su.save(new UsuarioVO("Lola", "Flores", "123456", "111", "lola@correo.com", LocalDate.of(1950, 3, 12), sr.findByNombre("ROLE_ADMIN").get()));
		su.save(new UsuarioVO("Manolo", "Escobar", "123456", "222", "manolo@correo.com", LocalDate.of(1975, 3, 9), sr.findByNombre("ROLE_USER").get()));
		assertNotNull(su.save(new UsuarioVO("Antonio", "Molina", "123456", "333", "antonio@correo.com", LocalDate.of(2005, 8, 15), sr.findByNombre("ROLE_USER").get())));
		//}catch (DataIntegrityViolationException e) {
			//System.out.println(e.getMessage());
		//}
		
	}
	// Insertamos usuario con el mismo dni
	@Test
	void test08() {
		try {
		assertNull(su.save(new UsuarioVO("Elvis", "Prestley", "123456", "333", "antonio@correo.com",
				LocalDate.of(2005, 8, 15), sr.findByNombre("ROLE_USER").get())));
		}catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
	}

	
	// Buscar usuario por email
	@Test
	public void test09() {
		
		assertEquals("Lola", su.findByEmail("lola@lola.com").getUsername());
	}

	// Insertamos ventas
	@Test
	public void test10() {
		sv.save(new VentaVO
				(4, 
						LocalDate.now(),
						LocalTime.of(19, 40),
						su.findByDni("222").get(), 
						sc.findByFechaAndHora(LocalDate.of(2024, 10, 12), LocalTime.of(20, 30)).get()));
		sv.save(new VentaVO(4, 
				LocalDate.now(),
				LocalTime.of(19, 50),
				su.findByDni("333").get(), 
				sc.findByFechaAndHora(LocalDate.of(2024, 10, 12), LocalTime.of(20, 30)).get()));
		assertNotNull(sv.save(new VentaVO(4, 
				LocalDate.now(),
				LocalTime.of(21, 40),
				su.findByDni("333").get(), 
				sc.findByFechaAndHora(LocalDate.of(2024, 11, 25), LocalTime.of(20, 30)).get())));
	}
//	
//	// Buscamos ventas de un concierto
	@Test
	public void test11() {
		List<VentaVO> ventas = sv.findByConciertoGrupoNombre("ABBA").get();
		assertEquals(2,  ventas.size());
		for (VentaVO venta : ventas) {
			System.out.println(venta);
		}
	}
//	
//	// Comprobamos que las ventas disminuyen las plazas
	@Test
	public void test12() {
		assertEquals(292, sc.findById(5).get().getPlazas());
	}
//	
//	// Buscamos las compras de un usuario
	@Test
	public void test13() {
		List<VentaVO> ventas = sv.findByUsuarioDni("333").get();
		assertEquals(2, ventas.size());
	}
//
//	
//	// Crea grupo que ya existe
	@Test
	public void test14() {
		try {
		assertNotNull(sg.save(new GrupoVO("ABBA", 4, "imagenAbba.img", "Pop", "Suecia", "Waterlooo", "Subtexto" )));
		}catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
	}

}
