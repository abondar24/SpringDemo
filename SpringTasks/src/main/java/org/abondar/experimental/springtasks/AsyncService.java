package org.abondar.experimental.springtasks;

import java.util.concurrent.Future;

/**
 * Created by abondar on 18.07.16.
 */
public interface AsyncService {
    void asyncTask();
    Future<String> asyncWithReturn(String name);
}
