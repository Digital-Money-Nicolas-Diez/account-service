package dh.backend.accounts.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import dh.backend.accounts.domain.enums.ActivitiesType;
import dh.backend.accounts.domain.exception.DomainIntegrity;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class Activities {
    private UUID id;
    private String name;
    private Float amount;
    private OffsetDateTime dated;
    private UUID userId;
    private String origin;
    private String destination;
    private ActivitiesType type;
    private int ORIGIN_DESTINATION_LEN = 22;

    public Activities(UUID id, String name, Float amount, OffsetDateTime dated, UUID userId, String origin,
            String destination, ActivitiesType type) {

        if (origin == null || origin.length() != ORIGIN_DESTINATION_LEN) {
            throw new DomainIntegrity("origin must be exactly " + ORIGIN_DESTINATION_LEN + " characters long");
        }

        if (destination == null || destination.length() != ORIGIN_DESTINATION_LEN) {
            throw new DomainIntegrity("destination must be exactly " + ORIGIN_DESTINATION_LEN + " characters long");
        }

        this.id = this.require(id);
        this.name = this.require(name);
        this.amount = this.require(amount);
        this.dated = this.require(dated);
        this.userId = this.require(userId);
        this.origin = this.require(origin);
        this.destination = this.require(destination);
        this.type = this.require(type);
    }

    private <T> T require(T field) {
        if (field == null) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        if (field instanceof String str && str.isBlank()) {
            throw new DomainIntegrity("Field cannot be null or blank");
        }

        return field;
    }

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "cvu")
    private AccountEntity originAccount;

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "cvu")
    private AccountEntity destinationAccount;
}
