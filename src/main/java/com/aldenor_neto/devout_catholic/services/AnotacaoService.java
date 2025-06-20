package com.aldenor_neto.devout_catholic.services;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aldenor_neto.devout_catholic.DevoutCatholicApplication;
import com.aldenor_neto.devout_catholic.model.Anotacao;
import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.repository.AnotacaoRepository;

import java.util.List;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository repository;

    private static Logger logger = LoggerFactory.getLogger(DevoutCatholicApplication.class);

    @Transactional
    public Anotacao saveOrUpdate(Anotacao entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    public void update(Anotacao entity) {
        saveOrUpdate(entity);
    }

    public List<Anotacao> findAll() {
        return repository.findAll();
    }

    public Anotacao findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! Not Found entity for this id! :(");
        });
    }

    @Transactional
    public void delete(Anotacao entity) {
        repository.delete(entity);
    }

    public void testId(Anotacao entity) {
        if (entity.getId() > 0 && entity.getDescricao().isBlank()) {
            logger.info("Valor correto");
        } else {
            logger.info("Valor incorreto");
        }
    }

    public List<Anotacao> findAllByCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUser(user);
    }

    public Anotacao findByIdAndCurrentUser(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findById(id)
            .filter(a -> a.getUser().getId().equals(user.getId()))
            .orElseThrow(() -> new NoResultException("Ops! Not Found entity for this id! :("));
    }

    @Transactional
    public Anotacao saveOrUpdateForCurrentUser(Anotacao entity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setUser(user);
        return repository.saveAndFlush(entity);
    }

    @Transactional
    public void deleteByIdAndCurrentUser(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Anotacao anotacao = repository.findById(id)
            .filter(a -> a.getUser().getId().equals(user.getId()))
            .orElseThrow(() -> new NoResultException("Ops! Not Found entity for this id! :("));
        repository.delete(anotacao);
    }
}
