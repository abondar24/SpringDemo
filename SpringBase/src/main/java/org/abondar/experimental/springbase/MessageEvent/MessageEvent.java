package org.abondar.experimental.springbase.MessageEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Created by abondar on 06.07.16.
 */
public class MessageEvent extends ApplicationEvent {
    private String msg;

    public MessageEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
