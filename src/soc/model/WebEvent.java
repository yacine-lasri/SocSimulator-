package soc.model;

import java.time.LocalDateTime;

public class WebEvent extends SecurityEvent {
    private final String method;
    private final String path;
    private final String payload;

    public WebEvent(String method,String path,String payload,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.method=method;
        this.path=path;
        this.payload=payload;
    }

    @Override
    public String describe() {
        return "Web event from "+getSourceIp()+": "+method+" " +path;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getPayload() {
        return payload;
    }
}
