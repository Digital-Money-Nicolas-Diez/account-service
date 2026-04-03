package dh.backend.accounts.infrastructure.web.dto;

import dh.backend.accounts.domain.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CvuAndAlias(
        @Schema(example = "0000176971169967822712")
        @NotNull(message = "User UUID cannot be null")
        String cvu,

        @Schema(example = "example.of.alias")
        String alias

) {
    public CvuAndAlias(Account account) {
        this(account.getCvu(), account.getAlias());
    }
}
