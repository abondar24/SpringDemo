package org.abondar.experimental.springbase;

import org.abondar.experimental.springbase.BeanLifeCycle.BeanLifeCycleCommand;
import org.abondar.experimental.springbase.FactoryBean.FactoryBeanCommand;
import org.abondar.experimental.springbase.HelloWorld.HelloWorldCommand;

import org.abondar.experimental.springbase.MessageEvent.MessageEventCommand;
import org.abondar.experimental.springdemo.command.CommandRunner;
import org.abondar.experimental.springdemo.command.Commands;

public class Base {

    public static void main(String[] args) {
        String arg = args[0].toUpperCase();
        CommandRunner cmdRunner = new CommandRunner();
        try {
            switch (Commands.valueOf(arg)){
                case HW:
                    HelloWorldCommand hw = new HelloWorldCommand();
                    cmdRunner.storeAndRun(hw);
                    break;
                case FB:
                    FactoryBeanCommand fb = new FactoryBeanCommand();
                    cmdRunner.storeAndRun(fb);
                    break;
                case BLC:
                    BeanLifeCycleCommand blc = new BeanLifeCycleCommand();
                    cmdRunner.storeAndRun(blc);
                    break;
                case ME:
                    MessageEventCommand me = new MessageEventCommand();
                    cmdRunner.storeAndRun(me);
                    break;
            }
        } catch (IllegalArgumentException ex){
            System.out.println("Unknown command");
        }

    }


}
