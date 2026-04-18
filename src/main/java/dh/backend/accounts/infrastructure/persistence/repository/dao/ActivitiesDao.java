package dh.backend.accounts.infrastructure.persistence.repository.dao;

import java.util.List;
import java.util.UUID;

import dh.backend.accounts.infrastructure.mappers.Mapper;
import org.springframework.stereotype.Repository;

import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.infrastructure.persistence.entity.ActivitiesEntity;
import dh.backend.accounts.infrastructure.persistence.repository.ActivitiesRepository;

@Repository
public class ActivitiesDao implements dh.backend.accounts.domain.repository.ActivitiesRepository {

    public ActivitiesRepository database;
    public ActivitiesDao(ActivitiesRepository database) {
        this.database = database;
    }
    private Mapper<Activities, ActivitiesEntity> mapper;

    public List<Activities> get(UUID userId) {
        return database.findByUserIdOrderByDatedDesc(userId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
