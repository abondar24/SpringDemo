package org.abondar.experimental.springaop.aroundadivce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class WorkerBean {
    private Logger logger = LoggerFactory.getLogger(WorkerBean.class);

    public void doWork(int numOfTimes) {
        for (int x = 0; x < numOfTimes; x++) {
            work();
        }
    }

    private void work() {
        logger.info("Say my name");

    }
}
