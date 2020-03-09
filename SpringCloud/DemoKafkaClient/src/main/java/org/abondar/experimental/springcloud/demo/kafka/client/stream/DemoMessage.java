package org.abondar.experimental.springcloud.demo.kafka.client.stream;


import java.io.Serializable;

public class DemoMessage implements Serializable {

    private static long SERIAL_VERSION_UID = -1L;

    private String body;
    private String id;

    public DemoMessage(){}

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}
