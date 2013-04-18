package idu0200.kliendid.validators;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.model.GroupDefinition;

import java.util.Map;

public class CustomerGroupDefinitionValidator extends ValidatorBase<GroupDefinition> {
    @Override
    public ValidationResult validate(Map<String, String[]> input, ValidationResult<GroupDefinition> result) {
        if (!input.containsKey("name")) {
            result.add("name", "Nimi on puudu");
        }
        result.resultObject.setName(input.get("name")[0].trim());

        return result;
    }
}
