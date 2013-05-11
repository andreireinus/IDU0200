package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;

import java.util.Map;

public class Validator<T> {
    public ValidationResult validate(Map<String, String[]> input, T source) {
        return validate(input, new ValidationResult<T>(source));
    }

    public ValidationResult validate(Map<String, String[]> input, ValidationResult<T> result) {
        return result;
    }


    protected String getValueString(Map<String, String[]> input, String key) {
        return getValueString(input.get(key));
    }

    protected String getValueString(String[] input) {
        StringBuilder sb = new StringBuilder();

        for (String v : input) {
            sb.append(v);
        }

        return sb.toString();
    }

    protected boolean tryParseInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ValidationResult<T> validate(T object) {
        ValidationResult<T> result = new ValidationResult<T>(object);

        return result;
    }
}
