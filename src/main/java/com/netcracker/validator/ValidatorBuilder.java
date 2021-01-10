package com.netcracker.validator;

import java.util.ArrayList;
import java.util.List;

/*
@author Lozovoy
@version 1.0
class validator builder

 */

public class ValidatorBuilder<T> {
    private final List<Condition<T>> conditions = new ArrayList<>();

    public ValidatorBuilder<T> add(Condition<T> condition) {
        conditions.add(condition);
        return this;
    }

    public Validator<T> build() {
        return new Validator<>(conditions);
    }
}
