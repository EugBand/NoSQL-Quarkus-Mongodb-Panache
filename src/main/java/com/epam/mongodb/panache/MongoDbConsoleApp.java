package com.epam.mongodb.panache;


import com.epam.mongodb.panache.service.ConsoleCommand;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MongoDbConsoleApp implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        if (args.length == 0 || args.length > 3){
            System.out.println("Wrong amount of argument");
            return 1;
        }
        ConsoleCommand command = ConsoleCommand.valueOf(args[0].toUpperCase());
        String arg1 = args.length > 1 ? args[1] : null;
        String arg2 = args.length == 3 ? args[2] : null;
        System.out.println(command.apply(arg1, arg2));
        return 0;
    }
}
