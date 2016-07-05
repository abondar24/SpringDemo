package org.abondar.experimental.PropertyEditor;


import org.abondar.experimental.BeanLifeCycle.SimpleBean;
import org.abondar.experimental.BeanLifeCycle.SimpleDestuctiveBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by abondar on 02.07.16.
 */

public class PropertyEditorRun {
    static Logger logger = LoggerFactory.getLogger(PropertyEditorRun.class);

    public static void main(String[] args) throws IOException {
        File temp = File.createTempFile("test","text");
        temp.deleteOnExit();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(PropertyEditorConfiguration.class);
        ctx.refresh();

        PropertyEditorBean propertyEditorBean = ctx.getBean(PropertyEditorBean.class);

    }

}
