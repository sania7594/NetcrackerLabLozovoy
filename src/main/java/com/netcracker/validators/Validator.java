package com.netcracker.validators;

/**
 * Validation class
 *
 * @author Alex Lozovoy
 */

public class Validator<T>{
    Object expected;
    Selector<Object, T> selector;
    Verifier<Object> verifier;


    /**
     * @param expected expected
     * @param selector selector
     * @param verifier verifier
     */
    public <U> Validator(U expected, Selector<U, T> selector, Verifier<U> verifier) {
        this.expected=expected;
        this.selector= (Selector<Object, T>) selector;
        this.verifier= (Verifier<Object>) verifier;
    }

    public  Message validate(T target){
        Object actual = selector.select(target);
        boolean isValid = verifier.verify(expected, actual);
        if (isValid){
            return new Message("Very good",Status.OK);
        }
        else{
            return new Message("Fatal error: expected = " + expected,Status.ERROR);
        }

    }

}
