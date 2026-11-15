package soc.model;

import java.time.LocalDateTime;

public class NetworkEvent extends SecurityEvent{
       private final int port;
       private final String protocol;

    public NetworkEvent(int port,String protocol,long id, LocalDateTime timestamp, String sourceip) {
        super(id, timestamp, sourceip);
        this.port=port;
        this.protocol=protocol;
    }

    @Override
    public String describe() {
        return "Network event from "+getSourceIp()+" on port "+port+" ("+protocol+")";
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }
}
