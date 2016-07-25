package org.abondar.experimental.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by abondar on 25.07.16.
 */
public class StepExecutionStatsListener extends StepExecutionListenerSupport {
    private static Logger logger = LoggerFactory.getLogger(StepExecutionStatsListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution){
        logger.info("Wrote: "+stepExecution.getWriteCount()
        + " items in step: "+stepExecution.getWriteCount());


        return null;
    }
}
