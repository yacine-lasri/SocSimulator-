package soc.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Alert {

    private final long id;
    private final String ruleName;
    private final Severity severity;
    private final SecurityEvent event;
    private final LocalDateTime createdAt;

    public Alert(long id, String ruleName, Severity severity,
                 SecurityEvent event, LocalDateTime createdAt) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Alert ID must be positive");
        }

        if (ruleName == null || ruleName.isBlank()) {
            throw new IllegalArgumentException(
                    "Rule name must not be blank");
        }

        this.id = id;
        this.ruleName = ruleName;
        this.severity = Objects.requireNonNull(
                severity, "Severity must not be null");
        this.event = Objects.requireNonNull(
                event, "Event must not be null");
        this.createdAt = Objects.requireNonNull(
                createdAt, "Creation date must not be null");
    }

    public long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Severity getSeverity() {
        return severity;
    }

    public SecurityEvent getEvent() {
        return event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}