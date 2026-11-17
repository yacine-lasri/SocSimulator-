package soc.rule;

import java.util.Optional;
import soc.model.Alert;
import soc.model.SecurityEvent;

public interface DetectionRule {

    String getName();

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Optional<Alert> evaluate(SecurityEvent event);
}