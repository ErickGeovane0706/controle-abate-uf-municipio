package ifpb.bancoDeDados.BancodeDados.entity;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Abate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ano;
    private int mes;
    private long quantidade;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "categoria_animal_id", nullable = false)
    private CategoriaAnimal categoriaAnimal;
}
