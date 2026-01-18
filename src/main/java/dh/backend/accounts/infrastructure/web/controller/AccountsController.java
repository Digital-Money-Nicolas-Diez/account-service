package dh.backend.accounts.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dh.backend.accounts.application.CreateUseCase;
import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.infrastructure.web.dto.CreateAccountDto;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/accounts")
public class AccountsController{
    

    private CreateUseCase createUseCase;
    private Logger logger = Logger.getLogger(AccountsController.class.getName());
    public AccountsController(CreateUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }
    @PostMapping("/create")
    public String createAccount(@RequestBody CreateAccountDto accountDto) {
        logger.info("Creating account for user: " + accountDto.user());
        Account account = new Account(accountDto.user(),null,null);
        createUseCase.execute(account);
        
        return "success";
    }
    

}
