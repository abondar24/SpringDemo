package org.abondar.experimental.springdemo.command;

import java.util.ArrayList;
import java.util.List;

public class CommandRunner {
    private  final List<Command> history = new ArrayList<>();

    public void storeAndRun(Command cmd){
        this.history.add(cmd);
        cmd.run();
    }
}
