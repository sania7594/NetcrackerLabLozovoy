package com.netcracker.parser;

/*
@author Lozovoy
@version 1.0
interface class parser
 */

public interface ClassParser {
    <T> T from(String source, Class<T> jClass);

    <T> String to(T object);
}
