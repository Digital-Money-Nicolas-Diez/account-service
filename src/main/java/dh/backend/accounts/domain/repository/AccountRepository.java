package dh.backend.accounts.domain.repository;
import dh.backend.accounts.domain.entity.Account;

public interface AccountRepository {
    void create(Account account);
}
