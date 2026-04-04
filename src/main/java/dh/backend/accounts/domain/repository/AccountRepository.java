package dh.backend.accounts.domain.repository;
import java.util.UUID;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;

public interface AccountRepository {
    void create(Account account);
    Account get(UUID uuid, UUID user);
    Account getByUserId(UUID user);
    void update(Account account);
}
