package dh.backend.accounts.infrastructure.config.security.router;

import dh.backend.accounts.infrastructure.config.security.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ActivitiesSecurityFilterChain extends SecurityTemplate {

    public ActivitiesSecurityFilterChain(JwtAuthConverter jwtAuthConverter) {
        super(jwtAuthConverter);
    }

    void setRoutes(HttpSecurity http) {
        http.securityMatcher(ApiConstants.ACTIVITIES_BASE + "/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, AppRoutes.ACTIVITIES_GET.getRoute()).authenticated()
                );
    }

    @Bean("ActivitiesSecurityBean")
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return super.securityFilterChain(http);
    }
}