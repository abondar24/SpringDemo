package org.abondar.experimental.MessageEvent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by abondar on 06.07.16.
 */
public class Publisher implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.ctx =applicationContext;
    }

    public void publish(String message) {
    ctx.publishEvent(new MessageEvent(this,message));
    }
}
