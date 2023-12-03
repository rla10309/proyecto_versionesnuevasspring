package com.dawes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dawes.servicioImpl.ServicioUsuarioImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	ServicioUsuarioImpl su;


	@Bean
	public BCryptPasswordEncoder encripta() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.getSharedObject(AuthenticationManagerBuilder.class)
			.userDetailsService(su)
			.passwordEncoder(encripta());

		
		http.authorizeHttpRequests(
				(authz) -> authz.requestMatchers("/", "/index", "/public/**", "/css/**", "/js/**", "/img/**", "/vistaconcierto",
						"/user/compra", "/public/formregistro",  "/registrousuario",  "/registro", "/index?logout")
						.permitAll()
						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login")
						.defaultSuccessUrl("/index")
						.permitAll())
				.logout((logout) -> logout
						.logoutSuccessUrl("/index?logout")
						.permitAll());
		//http.httpBasic(withDefaults())
		
		
		http.csrf(c -> c.disable());

		return http.build();
	}

}
