package dh.backend.accounts.infrastructure.persistence.entity;

import java.util.UUID;

import dh.backend.accounts.domain.entity.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID user;

    @Column(name = "cvu", nullable = false, unique = true)
    private String cvu;

    @Column(name = "alias", nullable = false, unique = true)
    private String alias;

    @Column(name = "balance", nullable = false, columnDefinition = "float default 0.0")
    private Float balance;

    public AccountEntity() {
        // for JPA
    }

    public static AccountEntity fromDomain(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setUser(account.getUser());
        entity.setCvu(account.getCvu());
        entity.setAlias(account.getAlias());
        entity.setBalance(account.getBalance());

        return entity;
    }

    public static Account toDomain(AccountEntity account) {
        Account acc = new Account(account.getId(), account.getCvu(), account.getAlias(), account.getBalance());
        return acc;
    }

    public AccountEntity(UUID id, UUID user, String cvu, String alias) {
        this.id = id;
        this.user = user;
        this.cvu = cvu;
        this.alias = alias;
    }

}
