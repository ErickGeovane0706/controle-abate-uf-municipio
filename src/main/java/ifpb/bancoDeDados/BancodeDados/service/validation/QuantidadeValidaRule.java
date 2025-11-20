package ifpb.bancoDeDados.BancodeDados.service.validation;

public class QuantidadeValidaRule implements ValidationRule {

    @Override
    public void validate(String[] linha) {
        String quantidadeStr = linha[5];

        try {
            long q = Long.parseLong(quantidadeStr);
            if (q < 0) {
                throw new IllegalArgumentException("Quantidade negativa: " + q);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantidade inválida: " + quantidadeStr);
        }
    }
}
