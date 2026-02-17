package dh.backend.accounts.infrastructure.config.security;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Read application.properties.yaml for configuring the JwtAuthConverter. Allows customization of the resource ID and the claim used for the UUID.
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
@Getter
@Setter
public class JwtAuthConverterProperties {

    private String resourceId;
	private String uuidClaim;

}
