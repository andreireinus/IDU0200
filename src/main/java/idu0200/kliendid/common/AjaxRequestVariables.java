package idu0200.kliendid.common;

import java.util.HashMap;

public class AjaxRequestVariables {
    private String command;
    private String format;
    private int id = 0;
    private String payload;

    private HashMap<String, Object> values = new HashMap<>();

    public AjaxRequestVariables(String payload, String command, String format) {
        this(payload, command, format, 0);
    }

    public AjaxRequestVariables(String payload, String command, String format, int id) {
        this.payload = payload;
        this.command = command;
        this.format = format;
        this.id = id;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public String getPayload() {
        return payload;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addValues(String key, Object value) {
        values.put(key, value);
    }

    public HashMap<String, Object> getValues() {
        return values;
    }
}
