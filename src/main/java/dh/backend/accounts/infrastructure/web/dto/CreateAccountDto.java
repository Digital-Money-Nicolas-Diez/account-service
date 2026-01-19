package dh.backend.accounts.infrastructure.web.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateAccountDto(
        @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @NotNull(message = "User UUID cannot be null")
        UUID user
) {}