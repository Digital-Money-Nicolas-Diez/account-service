package dh.backend.accounts.infrastructure.persistence.helpers;

import dh.backend.accounts.infrastructure.web.exception.ResourceNotFoundException;

import java.util.Optional;

public class QueryHelper {
    public static <T> T orElseThrow(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new ResourceNotFoundException(message));
    }
}