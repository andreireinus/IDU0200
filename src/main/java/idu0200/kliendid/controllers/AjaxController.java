package idu0200.kliendid.controllers;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.common.AjaxErrorResponse;
import idu0200.kliendid.dao.*;
import idu0200.kliendid.model.*;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@WebServlet(name = "Ajax", urlPatterns = "/ajax")
public class AjaxController extends XControllerBase {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ensureSession(request);
        response.setContentType("application/json");

        AjaxRequest variables = parseVariables(request);
//        if (variables.getController() == null) {
//            emptyResponse(request, response);
//        }
//
//        if (variables.getController().equals("customers")) {
//            handleCustomer(variables.getAction(), request, response);
//        } else if (variables.getController().equals("devices")) {
//            handleDevices(variables, request, response);
//        } else if (variables.getController().equals("groups")) {
//            handleGroups(variables, request, response);
//        } else if (variables.getController().equals("address")) {
//            handleAddress(variables, request, response);
//        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    private void handleAddress(AjaxRequest vars, HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();
        CustomerDao db = new CustomerDao(em);
        CustomerAddressDao dbAddress = new CustomerAddressDao(em);

        if (vars.getAction().equals("addressList")) {
            sendCustomerAddressList(vars, response, db);
        } else if (vars.getAction().equals("setPrimary")) {
            Customer customer = db.getById(vars.getId());
//            CustomerAddress address = dbAddress.getById((Long) vars.getValues().get("a"));
//            customer.setPrimaryAddress(address);

            sendCustomerAddressList(vars, response, db);
        }
        em.close();
    }



    private void handleGroups(AjaxRequest vars, HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();
        GroupDefinitionDao dbDefinition = new GroupDefinitionDao(em);
        CustomerDao dbCustomer = new CustomerDao(em);

        if (vars.getAction().equals("groupsList")) {
            writeResponse(response, dbDefinition.getGroupsList());
        } else if (vars.getAction().equals("customerGroups")) {
            Customer customer = dbCustomer.getById(vars.getId());
            writeResponse(response, customer.getGroups());
        } else if (vars.getAction().equals("toggleGroup")) {
            try {
                em.getTransaction().begin();

                Customer customer = dbCustomer.getById(vars.getId());
//                GroupDefinition definition = dbDefinition.getById((Long) vars.getValues().get("g"));

//                if (customer.inGroup(definition)) {
//                    Group group = customer.getGroup(definition);
//                    if (group != null) {
//                        em.remove(group);
//                    }
//                } else {
//                    createGroup(customer, definition);
//                }
                em.getTransaction().commit();
                emptyResponse(request, response);
            } catch (Exception e) {
                handleException(e, em, request, response);
            }
        } else if (vars.getAction().equals("addGroup")) {
            try {
                em.getTransaction().begin();

                // TODO: Validator
                Customer customer = dbCustomer.getById(vars.getId());
                GroupDefinition definition = new GroupDefinition();
                definition.setName((String) vars.getValues().get("n"));
                em.persist(definition);

                createGroup(customer, definition);

                em.getTransaction().commit();
                emptyResponse(request, response);
            } catch (Exception e) {
                handleException(e, em, request, response);
            }
        }

        em.close();
    }

    private void handleDevices(AjaxRequest vars, HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();

        CommunicationDeviceDao dbDevice = new CommunicationDeviceDao(em);
        CommunicationDeviceTypeDao dbType = new CommunicationDeviceTypeDao(em);

        if (vars.getAction().equals("customerList")) {
            List<CommunicationDevice> list = dbDevice.getListByCustomer(vars.getId());
            writeResponse(response, list);
        } else if (vars.getAction().equals("typeList")) {
            writeResponse(response, dbType.getList());
        } else if (vars.getAction().equals("update")) {
            try {
                em.getTransaction().begin();
                HashMap a = (HashMap) new JSONDeserializer().deserialize(vars.getPayload());
                if (a.get("o") instanceof CommunicationDevice) {
                    CommunicationDevice source = (CommunicationDevice) a.get("o");
                    CommunicationDevice target = dbDevice.getById(source.getId());

                    target.setType(source.getType());
                    target.setValueText(source.getValueText());
                    target.setOrderBy(source.getOrderBy());

                    em.persist(target);
                } else if (a.get("o") instanceof HashMap) {
                    HashMap allItems = (HashMap) a.get("o");

                    String valueText = (String) allItems.get("valueText");
                    long orderBy = (long) allItems.get("orderBy");
                    long typeId = (long) ((HashMap) allItems.get("type")).get("id");
                    Object customerId = ((HashMap) allItems.get("customer")).get("id");

                    CustomerDao dbCustomer = new CustomerDao(em);

                    CommunicationDevice device = new CommunicationDevice();

                    device.setOrderBy(orderBy);
                    device.setValueText(valueText);
                    device.setCustomer(dbCustomer.getById(Long.parseLong((String) customerId)));
                    device.setType(dbType.getById(typeId));

                    Date date = new Date();
                    device.setCreated(new Timestamp(date.getDate()));

                    em.persist(device);
                } else {
                    AjaxErrorResponse ajaxErrorResponse = new AjaxErrorResponse("");
                    writeResponse(response, ajaxErrorResponse);
                }

                em.getTransaction().commit();
                AjaxErrorResponse ajaxErrorResponse = new AjaxErrorResponse("");
                ajaxErrorResponse.isError = false;
                writeResponse(response, ajaxErrorResponse);
            } catch (Exception e) {
                handleException(e, em, request, response);
            }
        }

        em.close();
    }

    private void handleCustomer(String format, HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();
        CustomerDao db = new CustomerDao(em);

        List<Customer> list = db.getList();
        writeResponse(response, list);

        em.close();
    }


    private void sendCustomerAddressList(AjaxRequest vars, HttpServletResponse response, CustomerDao db) {
        Customer customer = db.getById(vars.getId());
        Object[] result = new Object[2];
        result[0] = customer.getPrimaryAddress();
        result[1] = customer.getAddresses();
        writeResponse(response, result);
    }


    private Group createGroup(Customer customer, GroupDefinition definition) {
        Date date = new Date();
        Group group = new Group();

        group.setCreated(new Timestamp(date.getTime()));
        group.setCreatedBy(getCurrentUser());
        group.setCustomer(customer);
        group.setDefinition(definition);

        return group;
    }


    protected void handleException(Exception e, EntityManager em, HttpServletRequest request, HttpServletResponse response) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        AjaxErrorResponse ajaxErrorResponse = new AjaxErrorResponse(e.getMessage());
        ajaxErrorResponse.isError = true;
        writeResponse(response, ajaxErrorResponse);
    }

    private AjaxRequest parseVariables(HttpServletRequest request) {
        AjaxRequest variables = new AjaxRequest("", "asdf");

        try {
            String payload = getPayload(request);
            HashMap<String, String> input = getPayloadVariables(payload);
            variables.setPayload(payload);

            variables.setAction(input.get("f"));

            for (String key : input.keySet()) {
                if (key.equals("c") || key.equals("f") || key.equals("v")) {
                    continue;
                }
                variables.addValues(key, input.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return variables;
    }

    private HashMap<String, String> getPayloadVariables(String payload) throws IOException {
        return new JSONDeserializer<HashMap<String, String>>().deserialize(payload);
    }

    //    private String payloadString = null;
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
//            return payloadString;
        } catch (Exception e) {
            return "{}";
        }
    }

    protected void writeResponse(HttpServletResponse response, Object object) {
        try {
            JSONSerializer serializer = new JSONSerializer();
            serializer.serialize(object, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void emptyResponse(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
