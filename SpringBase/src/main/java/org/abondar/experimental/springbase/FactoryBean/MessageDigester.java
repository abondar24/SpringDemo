package org.abondar.experimental.springbase.FactoryBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.security.MessageDigest;

/**
 * Created by abondar on 04.07.16.
 */
public class MessageDigester {
    private static Logger logger = LoggerFactory.getLogger(MessageDigester.class);

    private MessageDigest digest1;
    private MessageDigest digest2;

    @Autowired
    @Qualifier("shaDigest")
    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    @Autowired
    @Qualifier("defaultDigest")
    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }

    public void digest(String msg){
        logger.info("Using digest1");
        digest(msg,digest1);

        logger.info("Using digest2");
        digest(msg,digest2);

    }

    private void digest(String msg,MessageDigest digest){
        logger.info("Using algorithm: "+digest.getAlgorithm());
        digest.reset();
        byte[] bytes =msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println(out);
    }
}
