package soc.model;

import java.time.LocalDateTime;

public class NetworkEvent extends SecurityEvent{
       private int port;
       private String protocol;

    protected NetworkEvent(int port,String protocol,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.port=port;
        this.protocol=protocol;
    }

    @Override
    String describe() {
        return "";
    }
}
