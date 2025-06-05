package aldenor.devout_catholic.model.liturgia;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Leitura {

    private String referencia;
    private String titulo;
    private String texto;
}
