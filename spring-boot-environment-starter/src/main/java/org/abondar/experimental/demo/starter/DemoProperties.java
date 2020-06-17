package org.abondar.experimental.demo.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo.properties")
public class DemoProperties {

    private String prop1;

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }
}
