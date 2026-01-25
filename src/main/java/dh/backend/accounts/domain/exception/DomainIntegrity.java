package dh.backend.accounts.domain.exception;

public class DomainIntegrity extends RuntimeException {
    public DomainIntegrity(String message) {
        super(message);
    }
    
}
