package org.abondar.experimental.springrest;

/**
 * Created by abondar on 19.07.16.
 */
public class MyBean {

    Long id;
    String message;

    public MyBean(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
