package org.abondar.experimental.springaop.aopBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class MessageWriter {
    private Logger logger = LoggerFactory.getLogger(MessageWriter.class);

public void writeMessage(){
    logger.info("World");
}
}
