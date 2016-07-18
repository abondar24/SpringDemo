package org.abondar.experimental.springaop.securebeforeadvice;

import org.abondar.experimental.springaop.aopBase.AOPRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class SecureBean {
    static Logger logger = LoggerFactory.getLogger(AOPRun.class);

    public void writeSecureMessage(){
        logger.info("Security must be good");
    }
}
