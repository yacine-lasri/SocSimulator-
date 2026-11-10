package soc.model;

import java.time.LocalDateTime;

public class AuthEvent extends SecurityEvent{
        private String user;
        private boolean success;
    protected AuthEvent(String user,boolean success,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.user=user;
        this.success=success;
    }

    @Override
    String describe() {
        return "";
    }
}
