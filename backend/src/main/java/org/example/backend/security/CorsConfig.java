package org.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Credentials erlauben (z.B. für Cookies oder Header)
        config.setAllowCredentials(true);

        // Erlaubte Ursprünge (React-Frontend)
        config.setAllowedOrigins(List.of("http://localhost:5173"));

        // Erlaubte HTTP-Methoden
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Erlaubte Header
        config.setAllowedHeaders(List.of("*")); // Alternativ: List.of("Authorization", "Content-Type")

        // Falls du custom Header vom Server an den Client senden willst
        config.setExposedHeaders(List.of("Authorization"));

        // Konfiguration für alle API-Endpunkte setzen
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
