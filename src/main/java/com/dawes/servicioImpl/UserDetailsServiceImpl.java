//package com.dawes.servicioImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.dawes.modelo.UsuarioVO;
//import com.dawes.repositorios.UsuarioRepositorio;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	UsuarioRepositorio ur;
//
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final UsuarioVO usuario = ur.findByUsername(username).get();
//        if (usuario == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        UserDetails user = User.withUsername(usuario.getUsername()).password(usuario.getPassword()).authorities(usuario.getAuthorities()).build();
//        return user;
//    }
//
//}
