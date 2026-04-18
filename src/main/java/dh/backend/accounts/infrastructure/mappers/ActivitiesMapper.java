package dh.backend.accounts.infrastructure.mappers;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;
import dh.backend.accounts.infrastructure.persistence.entity.ActivitiesEntity;

public final class ActivitiesMapper implements Mapper<Activities, ActivitiesEntity> {

    @Override
    public Activities toDomain(ActivitiesEntity entity) {
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

    @Override
    public ActivitiesEntity fromDomain(Activities domain) {
        return null;
    }
}
