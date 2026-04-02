package dh.backend.accounts.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.domain.repository.ActivitiesRepository;
import dh.backend.accounts.infrastructure.web.exception.ResourceNotFoundException;

@Service
public class GetActivities {

    private ActivitiesRepository dataAccessObject;
    public GetActivities(ActivitiesRepository dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    public List<Activities> execute(UUID userId) {

        List<Activities> activities = dataAccessObject.get(userId);

        if (activities == null || activities.isEmpty()) {
            throw new ResourceNotFoundException("No activities found for user with id: " + userId);
        }
        return activities;
    }
}
