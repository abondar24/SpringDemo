package org.abondar.experimental.BeanLifeCycle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Created by abondar on 03.07.16.
 */
public class SimpleBean {
    private static Logger logger = LoggerFactory.getLogger(SimpleDestuctiveBean.class);
    private static final String DEFAULT_NAME = "James Bond";
    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    //init callback method
    @PostConstruct
    public void init() {
        logger.info("Initializing simple bean");

        if (name == null) {
            logger.info("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Set the age property of " + SimpleBean.class);
        }

    }

    @Override
    public String toString() {
        return
                name + ' ' + age ;
    }


}
