package com.aldenor_neto.devout_catholic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldenor_neto.devout_catholic.model.Anotacao;
import com.aldenor_neto.devout_catholic.model.User;
import java.util.List;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
    List<Anotacao> findByUser(User user);
}
