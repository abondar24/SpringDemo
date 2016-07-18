package org.abondar.experimental.springaop.proxies;

/**
 * Created by abondar on 17.07.16.
 */
public class DummySimpleBean implements SimpleBean{
    private long dummy;

    @Override
    public void advised() {
        dummy = System.currentTimeMillis();
    }

    @Override
    public void unadvised() {
         dummy = System.currentTimeMillis();
    }
}
