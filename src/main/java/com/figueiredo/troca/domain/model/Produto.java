package com.figueiredo.troca.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUTO_MODEL")
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NM_PRODUTO", nullable = false)
    private String nome;
}
