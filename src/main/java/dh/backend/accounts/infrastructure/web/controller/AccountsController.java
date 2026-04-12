package dh.backend.accounts.infrastructure.web.controller;

import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
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

    @Operation(summary = "Create a new account", description = "This endpoint is part of registration process.It creates a new EMPTY account with the user uuid provided. This endpoint is for intern consumption only")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "400", description = "Bad Request. Invalid UUID")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto accountDto) {
        createUseCase.execute(AccountFactory.create(accountDto.user()));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get account balance", description = "Get the balance of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "200", description = "Success response")
    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDto> getBalance(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new BalanceResponseDto(this.getUserAccount(token).getBalance()));
    }

    @Operation(summary = "Get CVU and alias", description = "Get the CVU and alias of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "200", description = "Success response")
    @GetMapping("/ID")
    public ResponseEntity<CvuAndAlias> getCvuAndAlias(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new CvuAndAlias(this.getUserAccount(token)));
    }
}
