package dh.backend.accounts.infrastructure.web.exception;


import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
