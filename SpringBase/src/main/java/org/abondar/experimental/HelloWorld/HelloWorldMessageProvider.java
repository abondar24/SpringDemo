package org.abondar.experimental.HelloWorld;

/**
 * Created by abondar on 03.07.16.
 */
public class HelloWorldMessageProvider implements MessageProvider{
    @Override
    public String getMessage() {
        return "Hello World";
    }
}
