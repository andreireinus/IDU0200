package idu0200.kliendid.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidationResult<T> {

    public T resultObject;
    private Class<T> classInfo;
    private HashMap<String, List<String>> messages;

    public ValidationResult(T object) {
        resultObject = object;
        messages = new HashMap<>();
    }

    public void add(String key, String message) {
        if (!messages.containsKey(key)) {
            messages.put(key, new ArrayList<String>());
        }
        messages.get(key).add(message);
    }

    public boolean isValid() {
        return (messages.size() == 0);
    }
}
