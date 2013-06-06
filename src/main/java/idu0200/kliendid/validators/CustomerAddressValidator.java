package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.CustomerAddress;

import java.util.HashMap;

public class CustomerAddressValidator extends Validator<CustomerAddress> {

    @Override
    public ValidationResult<CustomerAddress> validate(HashMap<String, Object> input, CustomerAddress object) {
        ValidationResult<CustomerAddress> result = new ValidationResult<CustomerAddress>(object);

        if (input.containsKey("zip")) {
            object.setZip(String.valueOf(input.get("zip")));
        }
        if (input.containsKey("house")) {
            object.setHouse(String.valueOf(input.get("house")));
        }
        if (input.containsKey("townCounty")) {
            object.setTownCounty(String.valueOf(input.get("townCounty")));
        }
        if (input.containsKey("county")) {
            object.setCounty(String.valueOf(input.get("county")));
        }
        if (input.containsKey("address")) {
            object.setAddress(String.valueOf(input.get("address")));
        }

        //

        if (object.getAddress() == null || object.getAddress().length() == 0) {
            result.add("address");
        }
        if (object.getCounty() == null || object.getCounty().length() == 0) {
            result.add("county");
        }
        if (object.getTownCounty() == null || object.getTownCounty().length() == 0) {
            result.add("townCounty");
        }
        if (object.getZip() == null || object.getZip().length() == 0) {
            result.add("zip");
        }
        if (object.getHouse() == null || object.getHouse().length() == 0) {
            result.add("house");
        }
        return result;
    }
}
