package org.abondar.experimental.springaop;

import org.abondar.experimental.springaop.aopBase.AOPCommand;
import org.abondar.experimental.springaop.aroundadivce.AroundAdviceCommand;
import org.abondar.experimental.springaop.framework.FrameworkCommand;
import org.abondar.experimental.springaop.framework.aspectJ.AspectJCommand;
import org.abondar.experimental.springaop.keygen.KeygenCommand;
import org.abondar.experimental.springaop.modifycheck.ModifyCheckCommand;
import org.abondar.experimental.springaop.poincuts.PointcutCommand;
import org.abondar.experimental.springaop.proxies.ProxyCommand;
import org.abondar.experimental.springaop.securebeforeadvice.SecureBeforeAdviceCommand;
import org.abondar.experimental.springdemo.command.CommandRunner;
import org.abondar.experimental.springdemo.command.Commands;

public class AOP {
    public static void main(String[] args) {
        if (args.length==0){
            System.out.println("Missing argument. Please check documentation for available argynebts");
            System.exit(0);
        }
        String arg = args[0].toUpperCase();
        CommandRunner cmdRunner = new CommandRunner();
        try {
            switch (Commands.valueOf(arg)){
                case AOP:
                    AOPCommand aop = new AOPCommand();
                    cmdRunner.storeAndRun(aop);
                    break;
                case AA:
                    AroundAdviceCommand aa = new AroundAdviceCommand();
                    cmdRunner.storeAndRun(aa);
                    break;
                case FR:
                    FrameworkCommand fr = new FrameworkCommand();
                    cmdRunner.storeAndRun(fr);
                    break;
                case AJ:
                    AspectJCommand aj = new AspectJCommand();
                    cmdRunner.storeAndRun(aj);
                    break;
                case KG:
                    KeygenCommand kg = new KeygenCommand();
                    cmdRunner.storeAndRun(kg);
                    break;
                case MC:
                    ModifyCheckCommand mc = new ModifyCheckCommand();
                    cmdRunner.storeAndRun(mc);
                    break;
                case PC:
                    PointcutCommand pc = new PointcutCommand();
                    cmdRunner.storeAndRun(pc);
                    break;
                case PX:
                    ProxyCommand px = new ProxyCommand();
                    cmdRunner.storeAndRun(px);
                    break;
                case SBA:
                    SecureBeforeAdviceCommand sba = new SecureBeforeAdviceCommand();
                    cmdRunner.storeAndRun(sba);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        } catch (IllegalArgumentException ex){
            System.out.println("Unknown command");
        }
    }
}
