package com.aldenor_neto.devout_catholic.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;

import com.aldenor_neto.devout_catholic.model.Anotacao;
import com.aldenor_neto.devout_catholic.services.AnotacaoService;

@RestController 
@RequestMapping("/anotacoes")
@CrossOrigin(origins = "*")
public class AnotacaoController {

    @Autowired
    private AnotacaoService service;

    @GetMapping
    public ResponseEntity<List<Anotacao>> getAll() {
        return ResponseEntity.ok().body(service.findAllByCurrentUser());
    }

    @GetMapping("{id}")
    public ResponseEntity<Anotacao> getOne(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.findByIdAndCurrentUser(id));
    }

    @PostMapping
    public ResponseEntity<Anotacao> save(@RequestBody Anotacao entity) {
        entity.setDataCriacao(LocalDateTime.now());
        Anotacao saved = service.saveOrUpdateForCurrentUser(entity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAnotacao(@PathVariable(value = "id") Long id, @RequestBody Anotacao entity) {
        if (!Objects.equals(entity.getId(), id)) {
            return ResponseEntity.badRequest().body("Ops! Id of entity is not equals as param 'id'! :(");
        } else {
            service.saveOrUpdateForCurrentUser(entity);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnotacao(@PathVariable(value = "id") Long id) {
        service.deleteByIdAndCurrentUser(id);
        return ResponseEntity.noContent().build();
    }

}
