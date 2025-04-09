package org.acme;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GreetingDTO {
    @NotNull(message = "Message cannot be null")
    @Size(min = 1, max = 100, message = "Message must be between 1 and 100 characters")
    private String message;

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public GreetingDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
