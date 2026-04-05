package dh.backend.accounts.application;

import org.springframework.stereotype.Service;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;

@Service
public class CreateAccount {


    private final AccountRepository repository;
    public CreateAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(Account account) {
        repository.create(account);
    }

}
