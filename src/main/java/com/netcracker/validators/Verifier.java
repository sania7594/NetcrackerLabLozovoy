package com.netcracker.validators;

@FunctionalInterface
public interface Verifier<T> {
    boolean verify(T expected, T actual);
}
