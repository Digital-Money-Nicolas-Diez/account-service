package dh.backend.accounts.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dh.backend.accounts.application.GetActivities;
import dh.backend.accounts.domain.entity.Activities;
import dh.backend.accounts.infrastructure.web.dto.GetLastActivities;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Accounts management API")
public class ActivitiesController {
    private GetActivities getActivities;

    public ActivitiesController(GetActivities getActivities) {
        this.getActivities = getActivities;
    }

    @Operation(summary = "Get last 5 activities", description = "Get the last 5 activities from authenticated user.")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "409", description = "Domain Integrity Error")
    @GetMapping("/ID/transactions")
    public ResponseEntity<List<GetLastActivities>> getActivities(JwtAuthenticationToken token) {
        UUID user = UUID.fromString(token.getName());
        List<Activities> activities = getActivities.execute(user);
        List<GetLastActivities> response = activities.stream()
                .map(activity -> new GetLastActivities(
                        activity.getId(),
                        activity.getName(),
                        activity.getAmount(),
                        activity.getDated(),
                        activity.getOrigin(),
                        activity.getDestination(),
                        activity.getType()))
                .toList();
        return ResponseEntity.ok(response);
    }

}
