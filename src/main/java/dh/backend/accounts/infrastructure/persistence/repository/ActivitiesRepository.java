package dh.backend.accounts.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dh.backend.accounts.infrastructure.persistence.entity.ActivitiesEntity;

public interface ActivitiesRepository extends JpaRepository<ActivitiesEntity, UUID> {
    List<ActivitiesEntity> findByUserIdOrderByDatedDesc(UUID userId);
}
