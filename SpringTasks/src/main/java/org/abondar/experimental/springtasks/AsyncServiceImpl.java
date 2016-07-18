package org.abondar.experimental.springtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by abondar on 18.07.16.
 */
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService{
    private  Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("Started async task execution");

        try {
            Thread.sleep(10000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        logger.info("Execution is over");
    }


    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        logger.info("Started async task with return execution");

        try {
            Thread.sleep(5000);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        logger.info("Execution with return is over");
        return new AsyncResult<>("Hello: "+name);
    }

}
