package org.abondar.experimental.springaop.poincuts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 17.07.16.
 */
public class SampleAnnotationBean {
    private Logger logger = LoggerFactory.getLogger(SampleBean.class);

    @AdviceRequired
    public void foo(int x){
        logger.info("Invoked foo() with: " +x);

    }


    public void bar(){logger.info("Invoked bar()");}
}

