package dh.backend.accounts.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.web.exception.ResourceNotFoundException;

@Service
public class GetByUuid {

    private AccountRepository repository;
    public GetByUuid(AccountRepository repository) {
        this.repository = repository;
    }

    public Account execute(UUID uuid, UUID user) {
        Account account = repository.get(uuid, user);

        if(account == null) throw new ResourceNotFoundException("Account not found");
        return account;
    }
}
