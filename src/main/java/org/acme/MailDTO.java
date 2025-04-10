package org.acme;
import jakarta.validation.constraints.NotNull;

public class MailDTO {
    @NotNull(message = "Recipient email cannot be null")
    private String to;
    @NotNull(message = "Subject cannot be null")
    private String subject;
    @NotNull(message = "Text cannot be null")
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
