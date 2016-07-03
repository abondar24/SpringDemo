package org.abondar.experimental.HelloWorld;


import javax.annotation.PostConstruct;

/**
 * Created by abondar on 03.07.16.
 */
public class SimpleBean {
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
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
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
