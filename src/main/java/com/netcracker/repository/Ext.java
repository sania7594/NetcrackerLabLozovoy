package com.netcracker.repository;
/*
@author Lozovoy
@version 1.0
 */

public class Ext {
    public static void append(StringBuilder sb, Object... objects) {
        for (Object o : objects)
            sb.append(o);
    }

    public static String newLine() {
        return System.lineSeparator();
    }
}
