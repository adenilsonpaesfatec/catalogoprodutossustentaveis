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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desabilita CSRF para simplificação.
            .authorizeRequests()
            .requestMatchers("/web/administracao/brinquedos").authenticated() // Protege este endpoint.
            .anyRequest().permitAll() // Libera os outros endpoints.
            .and()
            .formLogin()
                .loginPage("/web/administracao/login") // Define a página de login personalizada.
                .defaultSuccessUrl("/web/administracao/paineladministrativo", true) // Redireciona após login bem-sucedido.
                .permitAll() // Permite acesso ao login para usuários não autenticados.
            .and()
            .logout()
                .logoutUrl("/logout") // Define a URL para logout.
                .logoutSuccessUrl("/web/home") // Redireciona após logout.
                .invalidateHttpSession(true) // Invalida sessão.
                .deleteCookies("JSESSIONID"); // Remove cookies da sessão.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
