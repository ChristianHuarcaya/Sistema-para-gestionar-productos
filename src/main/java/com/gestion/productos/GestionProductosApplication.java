package com.gestion.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class GestionProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProductosApplication.class, args);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authz -> authz
	            .anyRequest().permitAll()
	        )
	        .csrf().disable()
	        .formLogin().disable();
	    return http.build();
	}


}
