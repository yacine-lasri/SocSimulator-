package soc.model;

import java.util.Objects;

public final class Analyst {

    private final long id;
    private final String name;
    private final AnalystLevel level;

    public Analyst(long id, String name, AnalystLevel level) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Analyst ID must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Analyst name must not be blank");
        }

        this.id = id;
        this.name = name;
        this.level = Objects.requireNonNull(
                level, "Analyst level must not be null");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AnalystLevel getLevel() {
        return level;
    }
}