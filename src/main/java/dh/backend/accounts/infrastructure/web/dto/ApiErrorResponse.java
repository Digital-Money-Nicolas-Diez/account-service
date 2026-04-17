package dh.backend.accounts.infrastructure.web.dto;

public class ApiErrorResponse {

    private ApiErrorResponse() {
    }

    public static final String UNAUTHORIZED =
            """
                    {
                      "code": "UNAUTHORIZED",
                      "message": "Full authentication is required to access this resource"
                    }
                    """;

    public static final String VALIDATION_ERROR =
            """
                    {
                      "code": "VALIDATION_ERROR",
                      "message": "Invalid UUID string"
                    }
                    """;

    public static final String DOMAIN_INTEGRITY_ERROR =
            """
                    {
                      "code": "DOMAIN_INTEGRITY_ERROR",
                      "message": "Account already exists for user"
                    }
                    """;

    public static final String NOT_FOUND =
            """
                    {
                      "code": "RESOURCE_NOT_FOUND",
                      "message": "Resource not found"
                    }
                    """;
}