package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.CustomerAddress;

import java.util.Map;

public class CustomerAddressValidator extends Validator<CustomerAddress> {

    @Override
    public ValidationResult validate(Map<String, String[]> input, ValidationResult<CustomerAddress> result) {

        if (input.containsKey("zip")) {
            result.resultObject.setZip(getValueString(input, "zip"));
        } else {
            result.add("zip", "Sisestamata");
        }

        if (input.containsKey("house")) {
            if (tryParseInteger(getValueString(input, "house"))) {
                result.resultObject.setHouse(getValueString(input, "house"));
            } else {
                result.add("house", "Majanumber pole numbrid");
            }

        } else {
            result.add("house", "Sisestamata");
        }

        if (input.containsKey("address")) {
            result.resultObject.setAddress(getValueString(input, "address"));
        } else {
            result.add("address", "Sisestamata");
        }

        if (input.containsKey("county")) {
            result.resultObject.setCounty(getValueString(input, "county"));
        } else {
            result.add("county", "Sisestamata");
        }

        if (input.containsKey("parish")) {
            result.resultObject.setParish(getValueString(input, "parish"));
        } else {
            result.add("parish", "Sisestamata");
        }

        if (input.containsKey("town_county")) {
            result.resultObject.setTownCounty(getValueString(input, "town_county"));
        } else {
            result.add("town_county", "Sisestamata");
        }

        if (input.containsKey("phone")) {
            result.resultObject.setPhone(getValueString(input, "phone"));
        } else {
            result.add("phone", "Sisestamata");
        }

        if (input.containsKey("email")) {
            result.resultObject.setEmail(getValueString(input, "email"));
        } else {
            result.add("email", "Sisestamata");
        }


        if (input.containsKey("sms")) {
            result.resultObject.setSms(getValueString(input, "sms"));
        } else {
            result.add("sms", "Sisestamata");
        }

        if (input.containsKey("mobile")) {
            result.resultObject.setMobile(getValueString(input, "mobile"));
        } else {
            result.add("mobile", "Sisestamata");
        }

        if (input.containsKey("note")) {
            result.resultObject.setNote(getValueString(input, "note"));
        } else {
            result.add("note", "Sisestamata");
        }
        return result;
    }


}
