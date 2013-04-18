package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;

import java.util.Map;

public class ValidatorBase<T> {
    public ValidationResult validate(Map<String, String[]> input, T source) {
        return validate(input, new ValidationResult<T>(source));
    }
    public ValidationResult validate(Map<String, String[]> input, ValidationResult<T> result) {
        return result;
    }
}
