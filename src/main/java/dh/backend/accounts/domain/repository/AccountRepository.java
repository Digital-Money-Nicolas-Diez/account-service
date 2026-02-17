package dh.backend.accounts.domain.repository;
import java.util.UUID;

import dh.backend.accounts.domain.entity.Account;

public interface AccountRepository {
    void create(Account account);
    Account get(UUID uuid, UUID user);
}
