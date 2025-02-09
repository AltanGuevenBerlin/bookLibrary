package org.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Stellt sicher, dass der CORS-Filter angewendet wird
                .and()
                .csrf(csrf -> csrf.disable()) // CSRF für APIs deaktivieren
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/book/**")).permitAll() // Erlaubt alle API-Requests ohne Authentifizierung
                        .anyRequest().authenticated() // Alle anderen Anfragen müssen authentifiziert sein
                );

        return http.build();
    }
}
