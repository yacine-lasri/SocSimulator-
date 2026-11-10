package soc.model;

import java.time.LocalDateTime;

public class WebEvent extends SecurityEvent {
    private String method;
    private String path;
    private String payload;

    protected WebEvent(String method,String path,String payload,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.method=method;
        this.path=path;
        this.payload=payload;
    }

    @Override
    String describe() {
        return "";
    }
}
