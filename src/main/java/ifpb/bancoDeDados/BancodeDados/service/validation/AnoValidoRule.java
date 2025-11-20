package ifpb.bancoDeDados.BancodeDados.service.validation;

import ifpb.bancoDeDados.BancodeDados.service.validation.ValidationRule;

public class AnoValidoRule implements ValidationRule {
    @Override
    public void validate(String[] linha) {
        String anoStr = linha[0];

        try {
            int ano = Integer.parseInt(anoStr);
            if (ano < 1900 || ano > 2100) {
                throw new IllegalArgumentException("Ano fora do intervalo permitido: " + ano);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ano inválido: " + anoStr);
        }
    }
}
