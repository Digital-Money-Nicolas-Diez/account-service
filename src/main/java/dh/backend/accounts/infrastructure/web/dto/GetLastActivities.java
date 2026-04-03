package dh.backend.accounts.infrastructure.web.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.domain.enums.ActivitiesType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record GetLastActivities(
        @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @NotNull(message = "User UUID cannot be null")
        UUID id,

        @Schema(example = "Nicolás")
        String name,

        @Schema(example = "100.50")
        Float amount,

        @Schema(example = "2024-06-01T12:00:00Z")
        OffsetDateTime dated,

        @Schema(example = "1234567890123456789012")
        String origin,

        @Schema(example = "1234567890123456789012")
        String destination,

        @Schema(example = "DEPOSIT")
        ActivitiesType type
) {
    public GetLastActivities(Activities activity) {
        this(
                activity.getId(),
                activity.getName(),
                activity.getAmount(),
                activity.getDated(),
                activity.getOrigin(),
                activity.getDestination(),
                activity.getType()
        );
    }
}