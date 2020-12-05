package com.netcracker.validators;

@FunctionalInterface
public interface Selector<T,E> {
    T select(E object);
}
