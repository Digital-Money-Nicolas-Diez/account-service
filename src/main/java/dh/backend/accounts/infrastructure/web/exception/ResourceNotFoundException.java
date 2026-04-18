package dh.backend.accounts.infrastructure.web.exception;


import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException implements Supplier<X> {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
