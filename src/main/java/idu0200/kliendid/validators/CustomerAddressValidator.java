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
            result.add("address", "Aadress sisestamata");
        }
        if (object.getCounty() == null || object.getCounty().length() == 0) {
            result.add("county", "Vald sisestamata");
        }
        if (object.getTownCounty() == null || object.getTownCounty().length() == 0) {
            result.add("townCounty", "Linn sisestamata");
        }
        if (object.getEmail() == null || object.getEmail().length() == 0) {
            result.add("email", "Email sisestamata");
        }
        if (object.getSms() == null || object.getSms().length() == 0) {
            result.add("sms", "SMSi number sisestamata");
        }
        if (object.getMobile() == null || object.getMobile().length() == 0) {
            result.add("mobile", "Mobiil sisestamta");
        }
        if (object.getNote() == null || object.getNote().length() == 0) {
            result.add("note", "Märkus sisestamata");
        }

        return result;
    }
}
