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
    public ResponseEntity<ApiResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_NOT_FOUND)
                .body(new ApiResponse("RESOURCE_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_UNAUTHORIZED)
                .body(new ApiResponse("UNAUTHORIZED", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> handleValidation(ValidationException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(new ApiResponse("VALIDATION_ERROR", ex.getMessage()));

    }

    @ExceptionHandler(DomainIntegrity.class)
    public ResponseEntity<ApiResponse> handleDomainIntegrity(DomainIntegrity ex) {
        return ResponseEntity
                .status(HttpStatus.SC_CONFLICT)
                .body(new ApiResponse("DOMAIN_INTEGRITY_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.SC_CONFLICT)
                .body(new ApiResponse("DATA_INTEGRITY_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleUnexpected(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("UNEXPECTED_ERROR", ex.getMessage()));
    }

}
