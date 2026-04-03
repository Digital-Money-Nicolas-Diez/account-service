package dh.backend.accounts.domain.entity;

import java.util.UUID;
import dh.backend.accounts.domain.exception.DomainIntegrity;
import lombok.Getter;

@Getter
public class Account {
    private static final int MIN_LENGTH_CVU = 22;
    private static final String ALIAS_FORMAT = "^[a-z]+\\.[a-z]+\\.[a-z]+$";

    private final UUID user;
    private final Float balance;
    private final String cvu;
    private final String alias;

    public Account(UUID user, String cvu, String alias, Float balance) {
        if (balance != null && balance < 0) {
            throw new DomainIntegrity("Balance cannot be negative");
        }

        if (cvu == null || cvu.length() != MIN_LENGTH_CVU) {
            throw new DomainIntegrity("CVU must be exactly " + MIN_LENGTH_CVU + " characters long");
        }

        if (alias == null || !alias.matches(Account.ALIAS_FORMAT)) {
            throw new DomainIntegrity("Alias is invalid");
        }

        this.balance = balance != null ? balance : 0;
        this.user = user;
        this.cvu = cvu;
        this.alias = alias;
    }
}
