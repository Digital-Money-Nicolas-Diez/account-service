package dh.backend.accounts.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record BalanceResponseDto(
        @Schema(example = "1000.0")
        Float balance
) {}