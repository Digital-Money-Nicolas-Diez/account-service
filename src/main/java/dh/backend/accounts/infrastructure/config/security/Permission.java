package dh.backend.accounts.infrastructure.config.security;

public enum Permission {
    ACCOUNT_WRITE("account.write"),
    ACCOUNT_READ("account.read");

    private final String value;

    Permission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}