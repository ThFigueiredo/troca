package com.figueiredo.troca.api.controller;


import com.figueiredo.troca.api.http.resources.request.ProdutoRequest;
import com.figueiredo.troca.api.http.resources.response.ProdutoResponse;
import com.figueiredo.troca.domain.model.Produto;
import com.figueiredo.troca.domain.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController extends BaseController {

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    public ProdutoController(ProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ProdutoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProdutoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Produto produto = produtoService.findById(id);
        ProdutoResponse response = modelMapper.map(produto, ProdutoResponse.class);
        return respondOk(response);

    }


   @ApiOperation(value = "Criar novo produto", nickname = "addProduto", notes = "Criar produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ProdutoRequest exampleModelRequest) {
       Produto request = modelMapper.map(exampleModelRequest, Produto.class);
       Produto created = produtoService.create(request);
       ProdutoResponse response = modelMapper.map(created, ProdutoResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Produto existente ", nickname = "updateProduto", notes = "Atualiza Produto", response = ProdutoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ProdutoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ExampleModel not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProdutoRequest request) {
        Produto data = modelMapper.map(request, Produto.class);
        produtoService.update(id, data);
    }


    @ApiOperation(value = "Deletar Produto existente ", nickname = "deleteProduto", notes = "deleta Produto", response = ProdutoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProdutoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Produto not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        produtoService.delete(id);
    }


    @ApiOperation(value = "Buscar Produto", nickname = "findAll", notes = "Multiple search parasm can be provided", response = Produto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Produto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Produto> produtoPage = produtoService.findAll(pageable);
        List<ProdutoResponse> content = produtoPage.stream()
                .map(item -> modelMapper.map(item, ProdutoResponse.class))
                .collect(Collectors.toList());
        Page<ProdutoResponse> produtoResponses = new PageImpl<>(content, pageable, produtoPage.getTotalElements());
        return respondOk(produtoResponses);
    }

}
