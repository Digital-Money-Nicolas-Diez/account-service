package dh.backend.accounts.infrastructure.web.dto;

import java.util.UUID;

public record CreateAccountDto(
        UUID user
) {}