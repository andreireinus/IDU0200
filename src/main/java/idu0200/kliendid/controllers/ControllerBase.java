package idu0200.kliendid.controllers;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.dao.EmployeeDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class ControllerBase extends HttpServlet {
    private static EntityManagerFactory entityManagerFactory;
    private HttpSession session;

    protected synchronized EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("idu0200");
        }
        return entityManagerFactory.createEntityManager();
    }

    protected void ensureSession(HttpServletRequest request) {
        session = request.getSession(true);
    }

    protected HttpSession getSession() {
        return session;
    }

    protected long getUserId() {
        Object value = getSession().getAttribute("user_id");
        if (value == null) {
            return 0;
        }
        return (long) value;
    }

    protected void setUserId(long id) {
        getSession().setAttribute("user_id", id);
    }

    public Employee getCurrentUser() {
        if (!isAuthenticated()) {
            return null;
        }

        EntityManager em = getEntityManager();
        EmployeeDao db = new EmployeeDao(em);
        Employee employee = db.getById(getUserId());
        em.close();

        return employee;
    }

    protected boolean isAuthenticated() {
        return (getUserId() > 0);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ensureSession(request);

        AjaxRequest ajaxRequest = getAjaxRequest(request);

        for (Method m : this.getClass().getMethods()) {
            if (m.getName().equals(ajaxRequest.getAction())) {
                try {
                    m.invoke(this, ajaxRequest, request, response);
                    break;
                } catch (IllegalAccessException e) {
                    handleException(e);
                } catch (InvocationTargetException e) {
                    handleException(e);
                }
            }
        }

        response.setCharacterEncoding("utf-8");
    }

    protected AjaxRequest getAjaxRequest(HttpServletRequest request) {
        AjaxRequest variables = new AjaxRequest("", "");

        try {
            String payload = getPayload(request);
            HashMap<String, Object> input = getPayloadVariables(payload);
            variables.setPayload(payload);

            variables.setAction((String) input.get("a"));

            if (input.containsKey("id")) {

                if (input.get("id") instanceof Long) {
                    variables.setId((Long) input.get("id"));
                } else if (input.get("id") instanceof String) {
                    String idString = (String) input.get("id");

                    if (idString != null && idString.length() > 0) {
                        try {
                            variables.setId(Long.parseLong(idString));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            for (String key : input.keySet()) {
                if (key.equals("a") || key.equals("id")) {
                    continue;
                }
                variables.addValues(key, input.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return variables;
    }

    private HashMap<String, Object> getPayloadVariables(String payload) throws IOException {
        return new JSONDeserializer<HashMap<String, Object>>().deserialize(payload);
    }

    private String getPayload(HttpServletRequest request) {
        try {
            InputStream stream = request.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(reader);

            String read = bufferedReader.readLine();
            while (read != null) {
                builder.append(read);
                read = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (Exception e) {
            return "{}";
        }
    }

    protected void writeResponse(HttpServletResponse response, Object object) throws IOException {
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.exclude("*.class").deepSerialize(object);
        response.getWriter().write(json);
    }

    protected void handleException(Exception e) {
        e.printStackTrace();
        // ignore
    }

    protected void handleException(Exception e, EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        handleException(e);
    }

    protected Timestamp getCurrentTimestamp() {
        return new Timestamp((new Date()).getTime());
    }
}
