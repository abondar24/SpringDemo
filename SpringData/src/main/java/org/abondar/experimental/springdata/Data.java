package org.abondar.experimental.springdata;

import org.abondar.experimental.springdata.jdbc.JdbcCommand;
import org.abondar.experimental.springdata.jpa.JpaCommand;
import org.abondar.experimental.springdata.mongo.MongoCommand;
import org.abondar.experimental.springdemo.command.CommandRunner;
import org.abondar.experimental.springdemo.command.Commands;

public class Data {

    public static void main(String[] args) {
        String arg = args[0].toUpperCase();
        CommandRunner cmdRunner = new CommandRunner();
        try {
            switch (Commands.valueOf(arg)){
                case JDBC:
                    JdbcCommand jdbc = new JdbcCommand();
                    cmdRunner.storeAndRun(jdbc);
                    break;
                case JPA:
                    JpaCommand jpa = new JpaCommand();
                    cmdRunner.storeAndRun(jpa);
                    break;
                case MG:
                    MongoCommand mg = new MongoCommand();
                    cmdRunner.storeAndRun(mg);
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
