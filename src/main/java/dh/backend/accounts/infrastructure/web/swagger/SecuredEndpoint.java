package dh.backend.accounts.infrastructure.web.swagger;

import dh.backend.accounts.infrastructure.web.dto.ApiErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

/**
 * Composed annotation that documents the standard 401 Unauthorized response
 * for JWT-protected endpoints. Apply at class level to cover all methods,
 * or at method level for granular control.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "401",
                description = "Missing or invalid Bearer token",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(value = ApiErrorResponse.UNAUTHORIZED)
                )
        )
})
public @interface SecuredEndpoint {
}
