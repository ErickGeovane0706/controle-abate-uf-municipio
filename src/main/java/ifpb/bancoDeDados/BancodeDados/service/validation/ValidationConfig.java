package ifpb.bancoDeDados.BancodeDados.service.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidationConfig {

    @Bean
    public ValidatorEngine validatorEngine() {
        return new ValidatorEngine(List.of(
                new AnoValidoRule(),
                new MesValidoRule(),
                new UfValidaRule(),
                new QuantidadeValidaRule()
        ));
    }
}
