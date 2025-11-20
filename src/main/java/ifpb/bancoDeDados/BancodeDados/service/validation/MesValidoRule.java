package ifpb.bancoDeDados.BancodeDados.service.validation;

public class MesValidoRule implements ValidationRule {

    @Override
    public void validate(String[] linha) {
        String mesStr = linha[1];

        try {
            int mes = Integer.parseInt(mesStr);
            if (mes < 1 || mes > 12) {
                throw new IllegalArgumentException("Mês inválido: " + mes);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Mês inválido: " + mesStr);
        }
    }
}
