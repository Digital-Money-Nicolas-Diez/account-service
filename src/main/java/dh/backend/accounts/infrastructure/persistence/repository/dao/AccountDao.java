package dh.backend.accounts.infrastructure.persistence.repository.dao;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
import dh.backend.accounts.infrastructure.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;

@Repository
public class AccountDao implements AccountRepository {

    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());

    private final dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database;

    public AccountDao(dh.backend.accounts.infrastructure.persistence.repository.AccountRepository database) {
        this.database = database;
    }

    private Account toDomain(Optional<AccountEntity> entity){
        return entity.map(AccountEntity::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public void create(Account account) {
        database.save(AccountEntity.fromDomain(account));
    }

    @Override
    public Account getByUserId(UUID user) {
        return this.toDomain(database.findByUser(user));
    }

    @Override
    public Account get(UUID uuid, UUID user) {
        return this.toDomain(database.findByIdAndUser(uuid, user));
    }

    @Override
    public void update(Account account){
        AccountEntity entity = AccountEntity.fromDomain(account);
        this.database.save(entity);
    }
}