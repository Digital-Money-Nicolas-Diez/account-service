package dh.backend.accounts.application;

import java.util.UUID;

import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Service;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.web.exception.ResourceNotFoundException;

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
