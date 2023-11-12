//package com.dawes.seguridad;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.dawes.servicioImpl.UserDetailsServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//	   @Autowired
//	    private UserDetailsServiceImpl userDetailsService;
//
//	    @Bean
//	    public DaoAuthenticationProvider authProvider() {
//	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	        authProvider.setUserDetailsService(userDetailsService);
//	        authProvider.setPasswordEncoder(encripta());
//	        return authProvider;
//	    }
//	
//	@Bean
//	public BCryptPasswordEncoder encripta() {
//		return new BCryptPasswordEncoder();
//	}
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/public/**", "/", "/index", "/css/**", "/js/**", "/img/**").permitAll()
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                ).formLogin((form) -> form
//                        .loginPage("/login").permitAll()
//                )
//                .logout((logout) -> logout.permitAll());
//        http.csrf(c->c.disable());
//        
//        return http.build();
//    }
//
//}
