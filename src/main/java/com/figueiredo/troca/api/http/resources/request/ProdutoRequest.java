package com.figueiredo.troca.api.http.resources.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

}
