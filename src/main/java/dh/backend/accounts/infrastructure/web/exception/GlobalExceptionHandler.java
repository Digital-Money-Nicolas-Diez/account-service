package dh.backend.accounts.infrastructure.web.exception;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dh.backend.accounts.domain.exception.DomainIntegrity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_NOT_FOUND)
                .body(new ApiErrorResponse("RESOURCE_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_UNAUTHORIZED)
                .body(new ApiErrorResponse("UNAUTHORIZED", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(ValidationException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(new ApiErrorResponse("VALIDATION_ERROR", ex.getMessage()));

    }

    @ExceptionHandler(DomainIntegrity.class)
    public ResponseEntity<ApiErrorResponse> handleDomainIntegrity(DomainIntegrity ex) {
        return ResponseEntity
                .status(HttpStatus.SC_CONFLICT)
                .body(new ApiErrorResponse("DOMAIN_INTEGRITY_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_CONFLICT)
                .body(new ApiErrorResponse("DATA_INTEGRITY_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse("UNEXPECTED_ERROR", ex.getMessage()));
    }

}
