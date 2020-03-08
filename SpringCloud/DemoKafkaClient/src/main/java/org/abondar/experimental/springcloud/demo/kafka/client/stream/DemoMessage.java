package org.abondar.experimental.springcloud.demo.kafka.client.stream;


public class DemoMessage {

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
