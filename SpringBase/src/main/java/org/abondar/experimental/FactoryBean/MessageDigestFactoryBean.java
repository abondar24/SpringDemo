package org.abondar.experimental.FactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;


import javax.annotation.PostConstruct;
import java.security.MessageDigest;

/**
 * Created by abondar on 04.07.16.
 */
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest> {


    @Value("${defAlgorithm}")
    private String algorithmName;

    private MessageDigest messageDigest = null;


    @Override
    public MessageDigest getObject() throws Exception {
        return messageDigest;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
    }
}
