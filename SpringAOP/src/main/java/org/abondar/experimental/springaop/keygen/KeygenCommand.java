package org.abondar.experimental.springaop.keygen;

import org.abondar.experimental.springaop.securebeforeadvice.SecurityAdvice;
import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class KeygenCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(SecurityAdvice.class);

    public void run() {
        KeyGenerator keyGenerator = getGenerator();

        for (int x=0;x<10;x++){
            try{
                long key = keyGenerator.getKey();
                logger.info("Key: "+ key);
            }catch (SecurityException ex){
                logger.info("Weak key generated");
            }
        }

    }

    private static KeyGenerator getGenerator(){
        KeyGenerator target = new KeyGenerator();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new WeakKeyCheckAdvice());

        return (KeyGenerator)factory.getProxy();
    }
}
