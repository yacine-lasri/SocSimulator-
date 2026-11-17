package soc.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Incident {

    private final long id;
    private final String title;
    private final IncidentStatus status;
    private final Severity severity;
    private final LocalDateTime openedAt;
    private final LocalDateTime closedAt;
    private final Analyst analyst;
    private final List<Alert> alerts;

    private Incident(Builder builder) {

        if (builder.id <= 0) {
            throw new IllegalArgumentException(
                    "Incident ID must be positive");
        }

        if (builder.title == null || builder.title.isBlank()) {
            throw new IllegalArgumentException(
                    "Incident title must not be blank");
        }

        this.id = builder.id;
        this.title = builder.title;

        this.status = Objects.requireNonNull(
                builder.status, "Status must not be null");

        this.severity = Objects.requireNonNull(
                builder.severity, "Severity must not be null");

        this.openedAt = Objects.requireNonNull(
                builder.openedAt, "Opening date must not be null");

        if (status == IncidentStatus.RESOLVED) {
            if (builder.closedAt == null
                    || builder.closedAt.isBefore(openedAt)) {
                throw new IllegalArgumentException(
                        "Resolved incident needs a valid closing date");
            }
        } else if (builder.closedAt != null) {
            throw new IllegalArgumentException(
                    "An active incident must not have a closing date");
        }

        this.closedAt = builder.closedAt;
        this.analyst = builder.analyst;
        this.alerts = List.copyOf(builder.alerts);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public Analyst getAnalyst() {
        return analyst;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    
    public Builder toBuilder() {

        Builder builder = new Builder()
                .id(id)
                .title(title)
                .status(status)
                .severity(severity)
                .openedAt(openedAt)
                .closedAt(closedAt)
                .analyst(analyst);

        for (Alert alert : alerts) {
            builder.addAlert(alert);
        }

        return builder;
    }
    public static class Builder {

        private long id;
        private String title;
        private IncidentStatus status = IncidentStatus.OPEN;
        private Severity severity;
        private LocalDateTime openedAt = LocalDateTime.now();
        private LocalDateTime closedAt;
        private Analyst analyst;
        private final List<Alert> alerts = new ArrayList<>();

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(IncidentStatus status) {
            this.status = status;
            return this;
        }

        public Builder severity(Severity severity) {
            this.severity = severity;
            return this;
        }

        public Builder openedAt(LocalDateTime openedAt) {
            this.openedAt = openedAt;
            return this;
        }

        public Builder closedAt(LocalDateTime closedAt) {
            this.closedAt = closedAt;
            return this;
        }

        public Builder analyst(Analyst analyst) {
            this.analyst = analyst;
            return this;
        }

        public Builder addAlert(Alert alert) {
            alerts.add(Objects.requireNonNull(
                    alert, "Alert must not be null"));
            return this;
        }

        public Incident build() {
            return new Incident(this);
        }
    }
}