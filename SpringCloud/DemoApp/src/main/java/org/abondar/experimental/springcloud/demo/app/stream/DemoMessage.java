package org.abondar.experimental.springcloud.demo.app.stream;

import java.util.UUID;

public class DemoMessage {

    private String body;
    private String id;

    public DemoMessage(String body) {
        this.body = body;
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}
