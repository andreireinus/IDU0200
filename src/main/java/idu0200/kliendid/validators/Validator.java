package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;

import java.util.HashMap;

public class Validator<T> {
    public ValidationResult<T> validate(HashMap<String, Object> values, T object) {
        ValidationResult<T> result = new ValidationResult<T>(object);

        return result;
    }

    protected boolean tryParseInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean tryParseLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
