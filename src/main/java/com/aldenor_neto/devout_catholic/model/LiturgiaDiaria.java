package com.aldenor_neto.devout_catholic.model;

import com.aldenor_neto.devout_catholic.model.liturgia.Antifonas;
import com.aldenor_neto.devout_catholic.model.liturgia.Leitura;
import com.aldenor_neto.devout_catholic.model.liturgia.Salmo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LiturgiaDiaria {

    private String data;
    private String liturgia;
    private String cor;
    private String dia;
    private String oferendas;
    private String comunhao;
    private Leitura primeiraLeitura;
    private Salmo salmo;
    private Object segundaLeitura;
    private Leitura evangelho;
    private Antifonas antifonas;

}

