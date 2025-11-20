package ifpb.bancoDeDados.BancodeDados.service.validation;

import java.util.List;

public class ValidatorEngine {

    private final List<ValidationRule> rules;

    public ValidatorEngine(List<ValidationRule> rules) {
        this.rules = rules;
    }

    public void validate(String[] linha) {
        for (ValidationRule rule : rules) {
            rule.validate(linha);
        }
    }
}
