package dh.backend.accounts.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/accounts/create").hasAuthority(Permission.ACCOUNT_WRITE.getValue())
                .requestMatchers(HttpMethod.GET, "/api/accounts/balance").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/accounts/ID/transactions/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/accounts/ID").authenticated()
                .anyRequest().permitAll());

        http.oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
