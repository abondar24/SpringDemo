package org.abondar.experimental.springaop.aopBase;

/**
 * Created by abondar on 16.07.16.
 */
public class ErrorBean {
    public void errorProneMethod() throws Exception{
        throw new Exception("Foo");
    }

    public void otherErrorProneMetod() throws IllegalArgumentException{
        throw new IllegalArgumentException("Bar");
    }
}
