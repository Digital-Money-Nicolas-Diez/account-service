package dh.backend.accounts.domain.repository;

import java.util.UUID;

import dh.backend.accounts.domain.entity.Account;

public interface AccountRepository {
    void create(Account account);
    Account getByUserId(UUID user);
    void update(Account account);
}
