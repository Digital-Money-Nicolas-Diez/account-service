package dh.backend.accounts.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dh.backend.accounts.application.CreateUseCase;
import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.infrastructure.web.dto.CreateAccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
public class AccountsController {

    private CreateUseCase createUseCase;
    private Logger logger = Logger.getLogger(AccountsController.class.getName());

    public AccountsController(CreateUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }

    @Operation(summary = "Create a new account", description = "This endpoint is part of registration process.It creates a new EMPTY account with the user uuid provided. This endpoint is for intern consumption only")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "400", description = "Bad Request. Invalid UUID")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto accountDto) {
        logger.info("Creating account for user: " + accountDto.user());
        Account account = new Account(accountDto.user(), null, null);
        createUseCase.execute(account);

        return ResponseEntity.ok().build();
    }

}
