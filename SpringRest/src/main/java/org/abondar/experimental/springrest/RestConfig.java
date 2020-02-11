package org.abondar.experimental.springrest;

import org.abondar.experimental.springdata.jpa.JPAConfig;
import org.abondar.experimental.springrest.jmx.AppStats;
import org.abondar.experimental.springrest.jmx.AppStatsImpl;
import org.springframework.context.annotation.*;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.abondar.experimental.springrest")
@Import(JPAConfig.class)
public class RestConfig extends WebMvcConfigurerAdapter{

    //jmx requires some hibernate shit which I don't understand where to take
    // commnent these beans to make rest demo operational
    @Bean
    public AppStats appStats() {
        return new AppStatsImpl();
    }

    @Bean
    public MBeanExporter jmxExporter(){
        MBeanExporter jmxExporter = new MBeanExporter();
        jmxExporter.setBeans(apps());
        return jmxExporter;
    }

    @Bean
    public HashMap<String,Object> apps(){
        HashMap<String,Object> apps = new HashMap<>();
        apps.put("SpringRestDemo",appStats());
        return apps;
    }


}
