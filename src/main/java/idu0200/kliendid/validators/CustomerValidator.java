package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.Customer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CustomerValidator extends Validator<Customer> {

    @Override
    public ValidationResult<Customer> validate(HashMap<String, Object> values, Customer customer) {
        ValidationResult<Customer> result = super.validate(values, customer);

        if (values.containsKey("firstName")) {
            customer.setFirstName(String.valueOf(values.get("firstName")));
        }
        if (values.containsKey("lastName")) {
            customer.setLastName(String.valueOf(values.get("lastName")));
        }
        if (values.containsKey("identityCode")) {
            customer.setIdentityCode(String.valueOf(values.get("identityCode")));
        }
        if (values.containsKey("birthDate")) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(String.valueOf(values.get("birthDate")));
                customer.setBirthDate(new Timestamp(date.getTime()));
            } catch (ParseException e) {
            }
        }


        if (customer.getFirstName() == null || customer.getFirstName().length() == 0) {
            result.add("firstName", "Eesinimi sisestamata");
        }

        if (customer.getLastName() == null || customer.getLastName().length() == 0) {
            result.add("lastName", "Perenimi sisestamata");
        }

        if (customer.getIdentityCode() == null || customer.getIdentityCode().length() == 0) {
            result.add("identityCode", "Isikukood sisestamata");
        }

        if (customer.getBirthDate() == null) {
            result.add("birthDate", "Synniaeg sisestamata");
        }

        return result;
    }
}
