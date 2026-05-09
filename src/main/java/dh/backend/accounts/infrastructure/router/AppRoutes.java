package dh.backend.accounts.infrastructure.router;

import lombok.Getter;


@Getter
public enum AppRoutes {
    ACCOUNT_CREATE(ApiConstants.ACCOUNTS_BASE + "/create"),
    ACCOUNT_BALANCE(ApiConstants.ACCOUNTS_BASE + "/balance"),
    ACTIVITIES_GET(ApiConstants.ACTIVITIES_BASE);

    private final String route;

    AppRoutes(String route) {
        this.route = route;
    }

}
