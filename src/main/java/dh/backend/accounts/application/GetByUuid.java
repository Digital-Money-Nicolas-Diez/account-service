package dh.backend.accounts.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;

@Service
public class GetByUuid {

    private final AccountRepository repository;

    public GetByUuid(AccountRepository repository) {
        this.repository = repository;
    }

    public Account execute(UUID user) {
        return repository.getByUserId(user);
    }
}
