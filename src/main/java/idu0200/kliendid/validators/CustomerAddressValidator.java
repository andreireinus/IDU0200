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
        if (input.containsKey("note")) {
            object.setNote(String.valueOf(input.get("note")));
        }
        if (input.containsKey("mobile")) {
            object.setMobile(String.valueOf(input.get("mobile")));
        }
        if (input.containsKey("phone")) {
            object.setPhone(String.valueOf(input.get("phone")));
        }
        if (input.containsKey("sms")) {
            object.setSms(String.valueOf(input.get("sms")));
        }
        if (input.containsKey("email")) {
            object.setEmail(String.valueOf(input.get("email")));
        }
        if (input.containsKey("townCounty")) {
            object.setTownCounty(String.valueOf(input.get("townCounty")));
        }
        if (input.containsKey("parish")) {
            object.setParish(String.valueOf(input.get("parish")));
        }
        if (input.containsKey("county")) {
            object.setCounty(String.valueOf(input.get("county")));
        }
        if (input.containsKey("address")) {
            object.setAddress(String.valueOf(input.get("address")));
        }
        if (input.containsKey("house")) {
            object.setSms(String.valueOf(input.get("house")));
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
        if (object.getEmail() == null || object.getEmail().length() == 0) {
            result.add("email");
        }
        if (object.getSms() == null || object.getSms().length() == 0) {
            result.add("sms");
        }
        if (object.getMobile() == null || object.getMobile().length() == 0) {
            result.add("mobile");
        }
        if (object.getZip() == null || object.getZip().length() == 0) {
            result.add("zip");
        }
        if (object.getHouse() == null || object.getHouse().length() == 0) {
            result.add("house");
        }
        if (object.getParish() == null || object.getParish().length() == 0) {
            result.add("parish");
        }
        if (object.getPhone() == null || object.getPhone().length() == 0) {
            result.add("phone");
        }
        return result;
    }
}
