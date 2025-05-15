package com.aldenor_neto.devout_catholic.model.biblia;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Livro {

    private String abbrev;
    private String name;
    private String[][] chapters;

}
