package org.abondar.experimental.demo.starter;

import java.util.logging.Logger;

public class SystemEnvironment {

    public SystemEnvironment(){
        System.out.println("Processors: "+Runtime.getRuntime().availableProcessors());
        System.out.println("Memory: "+Runtime.getRuntime().freeMemory());
    }
}
