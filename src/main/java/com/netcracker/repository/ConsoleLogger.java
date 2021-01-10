package com.netcracker.repository;
/*
@author Lozovoy
@version 1.0
class console logger

 */
public class ConsoleLogger implements Logger {
    @Override
    public void note(Object... args) {
        for (Object o : args)
            System.out.println(o);
    }

    @Override
    public void error(Object... args) {
        for (Object o : args)
            System.err.println(o);
    }
}
