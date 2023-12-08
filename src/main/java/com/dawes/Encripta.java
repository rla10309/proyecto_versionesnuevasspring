package com.dawes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encripta {

	public static void main(String[] args) {

		BCryptPasswordEncoder encripta = new BCryptPasswordEncoder();
		System.out.println(encripta.encode("123456"));
	}

}