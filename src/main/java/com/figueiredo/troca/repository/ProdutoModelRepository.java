package com.figueiredo.troca.repository;

import com.figueiredo.troca.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoModelRepository extends  JpaRepository<Produto, Long> {
}
