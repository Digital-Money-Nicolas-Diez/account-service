package dh.backend.accounts.infrastructure.config.security.router;

import dh.backend.accounts.infrastructure.config.security.JwtAuthConverter;
import dh.backend.accounts.infrastructure.config.security.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AccountSecurityFilterChain extends SecurityTemplate {

    public AccountSecurityFilterChain(JwtAuthConverter jwtAuthConverter) {
        super(jwtAuthConverter);
    }

    void setRoutes(HttpSecurity http) {
        http.securityMatcher(ApiConstants.ACCOUNTS_BASE + "/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, AppRoutes.ACCOUNT_CREATE.getRoute()).hasAuthority(Permission.ACCOUNT_WRITE.getValue())
                        .requestMatchers(HttpMethod.GET, AppRoutes.ACCOUNT_BALANCE.getRoute()).authenticated()
                        .anyRequest().authenticated());

    }


    @Bean("AccountSecurityBean")
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return super.securityFilterChain(http);
    }
}
