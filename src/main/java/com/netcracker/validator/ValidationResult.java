package com.netcracker.validator;

/*
@author Lozovoy
@version 1.0
class validator result

 */

public class ValidationResult {
    private final boolean isValid;
    private final String message;

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
