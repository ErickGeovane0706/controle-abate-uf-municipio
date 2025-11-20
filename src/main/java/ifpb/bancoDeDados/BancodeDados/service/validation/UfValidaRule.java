package ifpb.bancoDeDados.BancodeDados.service.validation;

public class UfValidaRule implements ValidationRule {

    private static final String[] UFS_VALIDAS = {
            "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
            "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
    };

    @Override
    public void validate(String[] linha) {
        String uf = linha[2];

        boolean valida = java.util.Arrays.asList(UFS_VALIDAS).contains(uf);

        if (!valida) {
            throw new IllegalArgumentException("UF inválida: " + uf);
        }
    }
}

