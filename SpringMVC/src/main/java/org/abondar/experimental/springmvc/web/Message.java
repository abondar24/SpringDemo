package org.abondar.experimental.springmvc.web;

/**
 * Created by abondar on 22.07.16.
 */
public class Message {

    private String type;
    private String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
