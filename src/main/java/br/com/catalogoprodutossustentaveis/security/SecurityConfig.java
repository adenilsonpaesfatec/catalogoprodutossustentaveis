package br.com.catalogoprodutossustentaveis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/web/administracao/**").authenticated()
	            .anyRequest().permitAll()
	        )
	        .formLogin(formLogin -> formLogin
	            .loginPage("/web/administracao/login")
	            .defaultSuccessUrl("/web/administracao/paineladministrativo", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/web/administracao/logout")
	            .logoutSuccessUrl("/web/home")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	        );
	    return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
