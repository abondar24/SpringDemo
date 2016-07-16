package org.abondar.experimental.springbase.HelloWorld;

/**
 * Created by abondar on 03.07.16.
 */
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
