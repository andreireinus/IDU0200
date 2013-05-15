package idu0200.kliendid.common;

import java.util.HashMap;

public class AjaxRequest {
    private String action;
    private Long id = 0L;
    private String payload;

    private HashMap<String, Object> values = new HashMap<>();

    public AjaxRequest(String payload, String action) {
        this(payload, action, 0L);
    }

    public AjaxRequest(String payload, String action, Long id) {
        this.payload = payload;
        this.action = action;
        this.id = id;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public String getPayload() {
        return payload;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addValues(String key, Object value) {
        values.put(key, value);
    }

    public HashMap<String, Object> getValues() {
        return values;
    }

    public Object getParameter(String key) {
        if (!values.containsKey(key)) {
            return null;
        }

        return values.get(key);
    }
}
