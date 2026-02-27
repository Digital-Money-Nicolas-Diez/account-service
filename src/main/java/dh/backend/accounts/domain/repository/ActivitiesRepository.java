package dh.backend.accounts.domain.repository;

import java.util.List;
import java.util.UUID;

import dh.backend.accounts.domain.entity.Activities;

public interface ActivitiesRepository {
    List<Activities> get(UUID user);
}
