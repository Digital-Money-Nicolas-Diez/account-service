package dh.backend.accounts.infrastructure.persistence.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.domain.enums.ActivitiesType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "activities")
public class ActivitiesEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "dated", nullable = false)
    private OffsetDateTime dated;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ActivitiesType type;

    public ActivitiesEntity() {
        // for JPA
    }

    public static Activities toDomain(ActivitiesEntity entity) {
        return new Activities(
                entity.getId(),
                entity.getName(),
                entity.getAmount(),
                entity.getDated(), 
                entity.getUserId(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getType());
    }


    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "cvu", insertable = false, updatable = false)
    private AccountEntity originAccount;

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "cvu", insertable = false, updatable = false)
    private AccountEntity destinationAccount;

}
