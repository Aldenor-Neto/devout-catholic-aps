package com.aldenor_neto.devout_catholic.controller;

import com.aldenor_neto.devout_catholic.model.biblia.Livro;
import com.aldenor_neto.devout_catholic.services.BibliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblia")
@CrossOrigin(origins = "*")
public class BibliaController {

    @Autowired
    private BibliaService services;

    @GetMapping("/{version}")
    public List<Livro> getBiblia(@PathVariable String version) {
        return services.getBiblia(version);
    }

    @GetMapping("/{version}/list-livros")
    public ResponseEntity<List<String>> listarLivros(@PathVariable String version) {
        List<String> livros = services.listarLivros(version);
        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{version}/{nome}")
    public ResponseEntity<Livro> getLivro(
            @PathVariable String version,
            @PathVariable String nome) {

        return services.getLivro(version, nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{version}/{nome}/{capitulo}")
    public ResponseEntity<String[]> getCapitulo(
            @PathVariable String version,
            @PathVariable String nome,
            @PathVariable int capitulo) {

        return services.getCapitulo(version, nome, capitulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{version}/{nome}/{capitulo}/{versiculo}")
    public ResponseEntity<String> getVersiculo(
            @PathVariable String version,
            @PathVariable String nome,
            @PathVariable int capitulo,
            @PathVariable int versiculo) {

        return services.getVersiculo(version, nome, capitulo, versiculo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
