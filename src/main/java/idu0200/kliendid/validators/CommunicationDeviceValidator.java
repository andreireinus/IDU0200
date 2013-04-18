package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.CommunicationDevice;

import java.util.Map;

public class CommunicationDeviceValidator extends ValidatorBase<CommunicationDevice> {
    @Override
    public ValidationResult validate(Map<String, String[]> input, ValidationResult<CommunicationDevice> result) {

        if (result.resultObject.getValueText().length() == 0) {
            result.add("valueText", "Nimetus puudu");
        }



        return result;
    }
}
