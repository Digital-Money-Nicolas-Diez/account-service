package dh.backend.accounts.infrastructure.web.controller;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.application.CreateUseCase;
import dh.backend.accounts.application.GetByUuid;
import dh.backend.accounts.infrastructure.web.dto.ApiErrorResponse;
import dh.backend.accounts.infrastructure.web.dto.BalanceResponseDto;
import dh.backend.accounts.infrastructure.web.dto.CreateAccountDto;
import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
import dh.backend.accounts.infrastructure.web.swagger.SecuredEndpoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
@SecuredEndpoint
public class AccountsController {

    private final CreateUseCase createUseCase;
    private final GetByUuid getByUuid;

    public AccountsController(CreateUseCase createUseCase, GetByUuid getByUuid) {
        this.createUseCase = createUseCase;
        this.getByUuid = getByUuid;
    }

    private Account getUserAccount(JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        return getByUuid.execute(user);
    }

    @Operation(
            summary = "Create account",
            description = """
                    Internal endpoint called automatically during the registration flow. \
                    Receives the user UUID from user-service via Feign and creates an empty \
                    account with a randomly generated CVU and alias.

                    ⚠️ Not intended for direct use — called internally by user-service.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(
            responseCode = "400",
            description = "Invalid UUID format in the request body",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = dh.backend.accounts.infrastructure.web.exception.ApiResponse.class),
                    examples = @ExampleObject(value = ApiErrorResponse.VALIDATION_ERROR)
            )
    )
    @ApiResponse(
            responseCode = "409",
            description = "Account already exists for this user",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = dh.backend.accounts.infrastructure.web.exception.ApiResponse.class),
                    examples = @ExampleObject(value = ApiErrorResponse.DOMAIN_INTEGRITY_ERROR)
            )
    )
    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto accountDto) {
        createUseCase.execute(AccountFactory.create(accountDto.user()));
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get account balance",
            description = """
                    Returns the current balance of the authenticated user's account.
                    The user is identified automatically from the JWT token — no ID needed.
                    Requires a valid Bearer token obtained via Keycloak login.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Balance returned successfully")
    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDto> getBalance(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new BalanceResponseDto(this.getUserAccount(token).getBalance()));
    }

    @Operation(
            summary = "Get CVU and alias",
            description = """
                    Returns the CVU and alias of the authenticated user's account. \
                    Both are generated randomly at account creation time. \
                    The user is identified automatically from the JWT token — no ID needed. \
                    Requires a valid Bearer token obtained via Keycloak login.
                    """
    )
    @ApiResponse(responseCode = "200", description = "CVU and alias returned successfully")
    @GetMapping("/ID")
    public ResponseEntity<CvuAndAlias> getCvuAndAlias(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new CvuAndAlias(this.getUserAccount(token)));
    }
}
