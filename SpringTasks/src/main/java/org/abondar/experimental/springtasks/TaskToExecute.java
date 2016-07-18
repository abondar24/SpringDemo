package org.abondar.experimental.springtasks;

import org.springframework.core.task.TaskExecutor;

/**
 * Created by abondar on 18.07.16.
 */
public class TaskToExecute {
    private TaskExecutor taskExecutor;

    public void executeTask(){
        for(int i=0;i<7;i++){
          taskExecutor.execute(() -> {
              System.out.println("Hello from thread: "
                      + Thread.currentThread().getName());
          });
        }
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
