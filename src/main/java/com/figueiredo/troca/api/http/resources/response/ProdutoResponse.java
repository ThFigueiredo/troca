package com.figueiredo.troca.api.http.resources.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    @ApiModelProperty(value = "Nome")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

}
