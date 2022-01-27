package com.figueiredo.troca.domain.repository;

import com.figueiredo.troca.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
