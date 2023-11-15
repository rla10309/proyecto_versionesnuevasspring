//package com.dawes.seguridad;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.dawes.modelo.UsuarioVO;
//import com.dawes.repositorios.UsuarioRepositorio;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	
//	@Autowired
//	UsuarioRepositorio usuarioRepositorio;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UsuarioVO usuario = usuarioRepositorio.findByUsername(username).get();
//		UserBuilder builder = null;
//		if(usuario !=null) {
//			builder = User.withUsername(username);
//			builder.disabled(false);
//			builder.password(usuario.getPassword());
//			builder.authorities(usuario.getAuthorities());
//		} else {
//			throw new UsernameNotFoundException("Usuario no encontrado");
//		}
//		return builder.build();
//	}
//
//}
