package com.netcracker.validator;
/*
@author Lozovoy
@version 1.0
interface selector

 */
@FunctionalInterface
public interface Selector<T, E> {
    T select(E object);
}
