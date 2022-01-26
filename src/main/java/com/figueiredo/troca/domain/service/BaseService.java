package com.figueiredo.troca.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E> {
    E create (E entity);
    E findById (Long id);
    Page<E> findAll (Pageable pageable);
    void update (Long id, E entity);
    void delete (Long id);
}
