package soc.ui;

import java.time.LocalDateTime;

import soc.model.Analyst;
import soc.model.AnalystLevel;
import soc.model.Incident;
import soc.model.IncidentStatus;
import soc.model.Severity;

public class TestIncident {

    public static void main(String[] args) {

        Incident incident = new Incident.Builder()
                .id(1)
                .title("Suspicious login attempts")
                .severity(Severity.HIGH)
                .build();

        Analyst analyst = new Analyst(
                1, "Analyst One", AnalystLevel.N1
        );

        Incident inProgress = incident.toBuilder()
                .analyst(analyst)
                .status(IncidentStatus.IN_PROGRESS)
                .build();

        Incident resolved = inProgress.toBuilder()
                .status(IncidentStatus.RESOLVED)
                .closedAt(LocalDateTime.now())
                .build();

        System.out.println("Original : " + incident.getStatus());
        System.out.println("Assigned : " + inProgress.getStatus());
        System.out.println("Analyst : "
                + inProgress.getAnalyst().getName());
        System.out.println("Final : " + resolved.getStatus());

        if (incident.getId() != resolved.getId()) {
            throw new AssertionError("Incident ID changed");
        }

        if (incident.getStatus() != IncidentStatus.OPEN) {
            throw new AssertionError("Original incident was modified");
        }

        if (resolved.getClosedAt() == null) {
            throw new AssertionError("Closing date is missing");
        }

        System.out.println("Checks OK");
    }
}