package org.acme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        // Create a structured error response
        Map<String, String> errors = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(), // Field name
                        ConstraintViolation::getMessage // Error message
                ));

        Map<String, Object> response = new HashMap<>();
        response.put("status", Response.Status.BAD_REQUEST.getStatusCode());
        response.put("error", "Validation failed");
        response.put("details", errors);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
