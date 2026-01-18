package dh.backend.accounts.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;

@Repository
public class AccountDao implements AccountRepository {
    
    private dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database;

    public AccountDao(dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database) {
        this.database = database;
    }

    @Override
    public void create(Account account) {
        database.save(AccountEntity.fromDomain(account));
    }
}
