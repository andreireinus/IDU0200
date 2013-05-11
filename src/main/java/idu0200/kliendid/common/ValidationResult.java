package idu0200.kliendid.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidationResult<T> {

    public T resultObject;
    private Class<T> classInfo;
    private HashMap<String, List<String>> messages;
    private List<Throwable> exceptions;

    public ValidationResult(T object) {
        resultObject = object;
        messages = new HashMap<>();
        exceptions = new ArrayList<>();
    }

    public void add(String key, String message) {
        if (!messages.containsKey(key)) {
            messages.put(key, new ArrayList<String>());
        }
        messages.get(key).add(message);
    }

    public boolean isValid() {
        return (messages.size() == 0) && (exceptions.size() == 0);
    }

    public void addException(Throwable e) {
        exceptions.add(e);
    }

    public String renderInlineHelp(String key) {
        StringBuilder sb = new StringBuilder();

        if (messages.containsKey(key)) {
            sb.append("<span class='help-inline'>");
            for (String message : messages.get(key)) {
                sb.append("<p>");
                sb.append(message);
                sb.append("</p>");
            }
            sb.append("</span>");
        }

        return sb.toString();
    }

    public String renderExceptionList() {
        StringBuilder sb = new StringBuilder();

        for (Throwable t : exceptions) {
            sb.append(renderException(t));
        }

        return sb.toString();
    }

    private String renderException(Throwable throwable) {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"alert alert-block\">");
        sb.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
        sb.append("<h4>" + throwable.getMessage() + "</h4>");

        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString());
            sb.append("<br />");
        }

        sb.append("</div>");
        return sb.toString();
    }

    public boolean isError(String key) {
        if (messages.containsKey(key)) {
            return true;
        }
        return false;
    }

    public String getErrorMessage(String key) {
        StringBuilder sb = new StringBuilder();

        if (!messages.containsKey(key)) {
            return sb.toString();
        }

        for (String message : messages.get(key)) {
            sb.append(message);
            sb.append("<br />");
        }

        return sb.toString();
    }
}
