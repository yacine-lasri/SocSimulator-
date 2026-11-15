package soc.UI;

import soc.model.AuthEvent;
import soc.model.NetworkEvent;
import soc.model.SecurityEvent;
import soc.model.WebEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        List<SecurityEvent> events = new ArrayList<>();
        events.add(new NetworkEvent(22, "TCP", 1, now, "192.168.1.5"));
        events.add(new WebEvent("GET", "/login?id=1", "", 2, now, "10.0.0.7"));
        events.add(new AuthEvent("admin", true, 3, now, "10.0.0.9"));

        for (SecurityEvent event : events) {
            System.out.println(event.describe());
        }
    }
}