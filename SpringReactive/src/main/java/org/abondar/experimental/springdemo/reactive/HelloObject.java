package org.abondar.experimental.springdemo.reactive;

public class HelloObject {

    private int id;
    private String greeting;

    public HelloObject(int id, String greeting) {
        this.id = id;
        this.greeting = greeting;
    }

    public HelloObject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "HelloObject{" +
                "id=" + id +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
