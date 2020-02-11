package org.abondar.experimental.springtasks;

import org.abondar.experimental.springdata.jpa.JPAConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created by abondar on 18.07.16.
 */
@Configuration
@Import(JPAConfig.class)
@EnableAsync
@EnableScheduling
@ComponentScan("org.abondar.experimental.springtasks")
public class TaskConfig {

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {

        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    TaskToExecute taskExecutorSample(){
        TaskToExecute task = new TaskToExecute();
        task.setTaskExecutor(taskExecutor());
        return task;
    }
}
