package soc.model;

import java.time.LocalDateTime;

public abstract class SecurityEvent {
    private final long id;
    private final LocalDateTime timeStamp;
    private final String sourceIp;


    protected SecurityEvent(long id, LocalDateTime timeStamp, String sourceIp) {
        this.id = id;
        this.timeStamp = timeStamp;
        if (sourceIp == null || !isValidIpv4(sourceIp)) {
            throw new IllegalArgumentException("Invalid IP address: " + sourceIp);
        }
        this.sourceIp = sourceIp;
    }

    private static boolean isValidIpv4(String ip){
        String [] parts=ip.split("\\.",-1);
        if (parts.length!=4)
            return false;
        for (String part : parts){
           if (!part.matches("\\d{1,3}"))
             return false;
           int value=Integer.parseInt(part);
           if (value>255)
               return false;
        }
    return true;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getSourceIp() {
        return sourceIp;
    }
   public abstract String describe();

}
