package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.CommunicationDevice;

import java.util.HashMap;

public class CommunicationDeviceValidator extends Validator<CommunicationDevice> {
    @Override
    public ValidationResult<CommunicationDevice> validate(HashMap<String, Object> values, CommunicationDevice object) {
        ValidationResult<CommunicationDevice> result = new ValidationResult<CommunicationDevice>(object);

        if (values.containsKey("valueText")) {
            object.setValueText(String.valueOf(values.get("valueText")));
        }
        if (values.containsKey("orderBy")) {
            Object val = values.get("orderBy");
            if (val instanceof String) {
                String s = (String) val;
                if (tryParseLong(s)) {
                    object.setOrderBy(Long.parseLong(s));
                } else {
                    result.add("orderBy", "J&auml;rjekord peab olema number.");
                }
            } else if (val instanceof Long) {
                object.setOrderBy((Long) val);
            }
        }

        if (object.getValueText() == null || object.getValueText().length() == 0) {
            result.add("valueText", "V&auml;&auml;rtus sisestamata.");
        }
        if (object.getOrderBy() == null) {
            result.add("orderBy", "J&auml;rjekord sisestamata.");
        }

        if (object.getCustomer() == null) {
            result.addException(new Exception("Klient m&auml;&auml;ramata."));
        }
        if (object.getType() == null) {
            result.addException(new Exception("T&uuml;&uuml;p m&auml;&auml;ramata."));
        }

        return result;
    }

}
