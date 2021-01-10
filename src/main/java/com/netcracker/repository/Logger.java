package com.netcracker.repository;
/*
@author Lozovoy
@version 1.0
class logger
 */

public interface Logger {
    void note(Object... args);

    void error(Object... args);
}
