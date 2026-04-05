package dh.backend.accounts.infrastructure.web.controller;

import dh.backend.accounts.application.UpdateCvuAndAlias;
import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
import org.springframework.web.bind.annotation.*;

import dh.backend.accounts.application.CreateAccount;
import dh.backend.accounts.application.GetUserAccount;
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

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
public class AccountsController {

    private final CreateAccount createAccount;
    private final GetUserAccount getUserAccount;
    private final UpdateCvuAndAlias patchAccountUseCase;

    public AccountsController(CreateAccount createAccount, GetUserAccount getUserAccount, UpdateCvuAndAlias patchAccountUseCase) {
        this.createAccount = createAccount;
        this.getUserAccount = getUserAccount;
        this.patchAccountUseCase = patchAccountUseCase;
    }

    private Account getUserAccount(JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        return getUserAccount.execute(user);
    }

    @Operation(summary = "Create a new account", description = "This endpoint is part of registration process.It creates a new EMPTY account with the user uuid provided. This endpoint is for intern consumption only")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "400", description = "Bad Request. Invalid UUID")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto accountDto) {
        createAccount.execute(AccountFactory.create(accountDto.user()));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get account balance", description = "Get the balance of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "200", description = "Success response")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/ID/balance")
    public ResponseEntity<BalanceResponseDto> getBalance(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new BalanceResponseDto(this.getUserAccount(token).getBalance()));
    }

    @Operation(summary = "Get account", description = "Get the cvu and alias of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "200", description = "Success response")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/ID")
    public ResponseEntity<CvuAndAlias> getAccount(JwtAuthenticationToken token) {
        return ResponseEntity.ok(new CvuAndAlias(this.getUserAccount(token)));
    }

    @Operation(summary = "Edit Cvu or Alias", description = "Edit the CVU or alias of an account by its UUID. Its necessary to login first to obtain the user UUID.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "200", description = "Success response")
    @ApiResponse(responseCode = "400", description = "Cvu or Alias invalid")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PatchMapping("/ID")
    public ResponseEntity<String> patchAccount(JwtAuthenticationToken token, @Valid @RequestBody CvuAndAlias body) {
        this.patchAccountUseCase.execute(body, UUID.fromString(token.getName()));
        return ResponseEntity.ok("Success");
    }
}
