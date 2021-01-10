package com.netcracker.validator;
/*
@author Lozovoy
@version 1.0
interface  verifier

 */

@FunctionalInterface
public interface Verifier<T> {
    boolean verify(T expected, T actual);
}
