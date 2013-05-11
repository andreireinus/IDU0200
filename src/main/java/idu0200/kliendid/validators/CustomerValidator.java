package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.Customer;

public class CustomerValidator extends Validator<Customer> {

    @Override
    public ValidationResult<Customer> validate(Customer o) {
        ValidationResult<Customer> result = new ValidationResult<Customer>(o);

        if (o.getFirstName() == null || o.getFirstName().length() == 0) {
            result.add("first_name", "Eesinimi sisestamata");
        }

        if (o.getLastName() == null || o.getLastName().length() == 0) {
            result.add("last_name", "Perenimi sisestamata");
        }

        if (o.getIdentityCode() == null || o.getIdentityCode().length() == 0) {
            result.add("identity_code", "Isikukood sisestamata");
        }

        return result;
    }
}
