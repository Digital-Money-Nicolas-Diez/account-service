package dh.backend.accounts.infrastructure.persistence.repository.dao;

import java.util.UUID;

import dh.backend.accounts.infrastructure.mappers.Mapper;
import dh.backend.accounts.infrastructure.persistence.helpers.QueryHelper;
import org.springframework.stereotype.Repository;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;

@Repository
public class AccountDao implements AccountRepository {

    private final dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database;
    private final Mapper<Account, AccountEntity> mapper;

    public AccountDao(dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database, Mapper<Account, AccountEntity> mapper) {
        this.database = database;
        this.mapper = mapper;
    }

    @Override
    public void create(Account account) {
        database.save(this.mapper.fromDomain(account));
    }

    @Override
    public void update(Account account) {
        AccountEntity entity = this.mapper.fromDomain(account);
        this.database.save(entity);
    }

    @Override
    public Account getByUserId(UUID user) {
        AccountEntity account = QueryHelper.orElseThrow(database.findByUser(user), "User account not found");
        return this.mapper.toDomain(account);
    }
}