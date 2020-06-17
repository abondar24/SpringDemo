package org.abondar.experimental.demo.starter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SystemEnvironment.class)
@EnableConfigurationProperties(DemoProperties.class)
public class EnvConfig implements InitializingBean {

    private DemoProperties properties;

    public EnvConfig(DemoProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemEnvironment systemEnvironment() {
        return new SystemEnvironment();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(properties.getProp1());
    }
}
