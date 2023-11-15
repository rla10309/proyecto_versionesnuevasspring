package com.dawes.modelo;





import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", uniqueConstraints = @UniqueConstraint(columnNames = {"dni"}))
public class UsuarioVO implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	@Column(length = 40, nullable = false)
	private String username;
	@Column(length = 60, nullable = false)
	private String apellidos;
	
	private String password;
	@Column(length = 15, nullable = false)
	private String dni;
	@Column(length = 100, nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="idrol")
	private RolVO rol;

	public UsuarioVO(String username, String apellidos, String password, String dni, String email, RolVO rol) {
		super();
		this.username = username;
		this.apellidos = apellidos;
		this.password = password;
		this.dni = dni;
		this.email = email;
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioVO [idusuario=" + idusuario + ", username=" + username + ", apellidos=" + apellidos
				+ ", password=" + password + ", dni=" + dni + ", email=" + email + ", rol=" + rol.getNombre() + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(this.rol.toString()));
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

		
	
	
	

}
