package soc.model;

import java.time.LocalDateTime;

public class AuthEvent extends SecurityEvent{
        private final String user;
        private final boolean success;
    public AuthEvent(String user,boolean success,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.user=user;
        this.success=success;

    }


    @Override
    public String describe() {
        return "Auth event from "+getSourceIp()+": user "+user+", login "+(success? "succeeded":"failed");
    }

    public String getUser() {
        return user;
    }

    public boolean isSuccess() {
        return success;
    }
}
