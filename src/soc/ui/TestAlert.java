package soc.ui;

import java.time.LocalDateTime;
import soc.model.Alert;
import soc.model.AuthEvent;
import soc.model.Severity;

public class TestAlert {

    public static void main(String[] args) {

        AuthEvent event = new AuthEvent(
                "admin",
                false,
                1,
                LocalDateTime.now(),
                "10.0.0.9"
        );

        Alert alert = new Alert(
                1,
                "BruteForceRule",
                Severity.HIGH,
                event,
                LocalDateTime.now()
        );

        System.out.println("ID : " + alert.getId());
        System.out.println("Rule : " + alert.getRuleName());
        System.out.println("Severity : " + alert.getSeverity());
        System.out.println("Event : " + alert.getEvent().describe());
        System.out.println("Created at : " + alert.getCreatedAt());

        try {
            new Alert(
                    2, " ", Severity.HIGH,
                    event, LocalDateTime.now()
            );

            throw new AssertionError("Blank rule name was accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("Validation OK : " + e.getMessage());
        }
    }
}