package dh.backend.accounts.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dh.backend.accounts.application.CreateUseCase;
import dh.backend.accounts.application.GetByUuid;
import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.infrastructure.web.dto.BalanceResponseDto;
import dh.backend.accounts.infrastructure.web.dto.CreateAccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
public class AccountsController {

    // private Logger logger = Logger.getLogger(AccountsController.class.getName());
    private CreateUseCase createUseCase;
    private GetByUuid getByUuid;

    public AccountsController(CreateUseCase createUseCase, GetByUuid getByUuid) {
        this.createUseCase = createUseCase;
        this.getByUuid = getByUuid;
    }

    @Operation(summary = "Create a new account", description = "This endpoint is part of registration process.It creates a new EMPTY account with the user uuid provided. This endpoint is for intern consumption only")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "400", description = "Bad Request. Invalid UUID")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto accountDto) {
        Account account = new Account(accountDto.user(), null, null, null);
        createUseCase.execute(account);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get account balance", description = "Get the balance of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @GetMapping("/balance/{accountId}")
    public ResponseEntity<BalanceResponseDto> getBalance(@PathVariable UUID accountId, JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        Account acccount = getByUuid.execute(accountId, user);
        return ResponseEntity.ok(new BalanceResponseDto(acccount.getBalance()));
    }

    @Operation(summary = "Get account balance", description = "Get the balance of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @GetMapping("/balance/{accountId}")
    public ResponseEntity<BalanceResponseDto> getAccount(@PathVariable UUID accountId, JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        Account acccount = getByUuid.execute(accountId, user);
        return ResponseEntity.ok(new BalanceResponseDto(acccount.getBalance()));
    }

}
