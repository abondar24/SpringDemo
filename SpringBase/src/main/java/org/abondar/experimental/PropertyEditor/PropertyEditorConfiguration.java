package org.abondar.experimental.PropertyEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
public class PropertyEditorConfiguration {

    @Autowired
    Environment env;

    @Bean
    CustomEditorConfigurer customEditorConfigurer() {
    CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setPropertyEditorRegistrars(propertyEditorRegistrars());
     return customEditorConfigurer;
    }

    @Bean
    CustomPropertyEditorRegistrar[] propertyEditorRegistrars(){
        CustomPropertyEditorRegistrar[] customPropertyEditorRegistrars = new CustomPropertyEditorRegistrar[]();
        //customPropertyEditorRegistrars[0] = new CustomPropertyEditorRegistrar().registerCustomEditors(builtinSample());
        return customPropertyEditorRegistrars;
    }

    @Bean
    PropertyEditorBean builtinSample() throws Exception {
        PropertyEditorBean propertyEditorBean = new PropertyEditorBean();

        propertyEditorBean.setBytes(env.getProperty("bytes").getBytes());
        propertyEditorBean.setCls(Class.forName((env.getProperty("class"))));
        propertyEditorBean.setTrueOrFalse(Boolean.valueOf(env.getProperty("trueOrFalse")));
        propertyEditorBean.setStream(new ByteArrayInputStream(env.getProperty("streamSource").getBytes()));
        propertyEditorBean.setFloatVal(Float.valueOf(env.getProperty("floatVal")));
        propertyEditorBean.setDate(new Date());
        propertyEditorBean.setLocale(new Locale(env.getProperty("locale")));
        propertyEditorBean.setPattern(Pattern.compile(env.getProperty("pattern")));

        Properties props = new Properties();
        props.setProperty("name","Alex");
        props.setProperty("lastName","Bondar");

        propertyEditorBean.setProperties(props);
        propertyEditorBean.setTrimString(env.getProperty("trimStr"));
        propertyEditorBean.setUrl(new URL(env.getProperty("url")));
        propertyEditorBean.setStringList(stringList());

       return propertyEditorBean;
    }

    @Bean
    List<String> stringList(){
        List<String> stringList = new ArrayList<>();
        stringList.add("String 1");
        stringList.add("String 2");

        return stringList;
    }
}
