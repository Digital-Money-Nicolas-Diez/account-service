package dh.backend.accounts.infrastructure.web.dto;

import dh.backend.accounts.domain.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CvuAndAlias(
        @Schema(example = "0000176971169967822712")
        @Size(min = Account.MIN_LENGTH_CVU, max = Account.MIN_LENGTH_CVU, message = "Cvu must be 22 digits")
        @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+$", message = "Alias format invalid")
        String cvu,

        @Schema(example = "example.of.alias")
        @Pattern(regexp = Account.ALIAS_FORMAT, message = "Alias format invalid")
        String alias

) {
    public CvuAndAlias(Account account) {
        this(account.getCvu(), account.getAlias());
    }
}
