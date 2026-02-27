package dh.backend.accounts.infrastructure.persistence.repository.dao;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.exception.DomainIntegrity;
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
        try {
            database.save(AccountEntity.fromDomain(account));
        } catch (DataIntegrityViolationException e) {
            throw new DomainIntegrity("Database integrity violation: " + e.getMessage());
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Account get(UUID uuid, UUID user) {
        try {
            return database.findByIdAndUser(uuid, user).map(AccountEntity::toDomain).orElse(null);
        } catch (DataIntegrityViolationException e) {
            throw new DomainIntegrity("Database integrity violation: " + e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }
}
