package dh.backend.accounts.infrastructure.config.security.router;

import dh.backend.accounts.infrastructure.config.security.JwtAuthConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public abstract class SecurityTemplate {

    private final JwtAuthConverter jwtAuthConverter;

    public SecurityTemplate(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    protected SecurityFilterChain securityFilterChain(HttpSecurity http) {
        setRoutes(http);
        http.oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    abstract void setRoutes(HttpSecurity http);
}
