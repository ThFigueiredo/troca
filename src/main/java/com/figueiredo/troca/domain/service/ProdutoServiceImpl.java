package com.figueiredo.troca.domain.service;

import com.figueiredo.troca.domain.exception.NotFoundException;
import com.figueiredo.troca.domain.model.Produto;
import com.figueiredo.troca.domain.repository.ProdutoRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @Override
    public Produto create(Produto entity) {
        return produtoRepository.save(entity);
    }

    @Override
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Organização não encontrada"));
    }

    @Override
    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public void update(Long id, Produto entity) {
        Produto exampleModel = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, exampleModel);

        produtoRepository.save(exampleModel);
    }

    @Override
    public void delete(Long id) {
        Produto exampleModel = findById(id);
        produtoRepository.delete(exampleModel);
    }
}
