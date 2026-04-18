package dh.backend.accounts.infrastructure.mappers;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public final class AccountMapper implements Mapper<Account, AccountEntity> {
    @Override
    public Account toDomain(AccountEntity entity) {
        return AccountFactory.create(entity.getUser(), entity.getCvu(), entity.getAlias(), entity.getBalance(), entity.getId());
    }

    @Override
    public AccountEntity fromDomain(Account domain) {
        AccountEntity entity = new AccountEntity();
        entity.setUser(domain.getUser());
        entity.setCvu(domain.getCvu());
        entity.setAlias(domain.getAlias());
        entity.setBalance(domain.getBalance());
        entity.setId(domain.getId());

        return entity;
    }
}
