package dh.backend.accounts.infrastructure.web.controller;

import dh.backend.accounts.application.GetActivities;
import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.infrastructure.web.dto.GetLastActivities;
import dh.backend.accounts.infrastructure.web.swagger.SecuredEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
@SecuredEndpoint
public class ActivitiesController {

    private final GetActivities getActivities;

    public ActivitiesController(GetActivities getActivities) {
        this.getActivities = getActivities;
    }

    @Operation(
            summary = "Get transaction history",
            description = """
                    Returns the full transaction history of the authenticated user's account.
                    Each entry includes the transaction ID, name, amount, date, origin CVU, \
                    destination CVU, and type (TRANSFER or DEPOSIT).
                    The user is identified automatically from the JWT token — no ID needed.
                    Requires a valid Bearer token obtained via Keycloak login.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Transaction history returned successfully")
    @GetMapping("/ID/transactions")
    public ResponseEntity<List<GetLastActivities>> getActivities(JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        Stream<Activities> activities = getActivities.execute(user).stream();
        return ResponseEntity.ok(activities.map(GetLastActivities::new).toList());
    }
}
