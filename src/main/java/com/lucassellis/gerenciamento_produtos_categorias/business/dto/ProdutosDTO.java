// ProdutosDTO.java
package com.lucassellis.gerenciamento_produtos_categorias.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosDTO {

    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    // @Positive: Não deixa o preço ser zero ou negativo (ninguém dá brinquedo de graça!)
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    private Double preco;

    // DTO vs ENTITY: No DTO a gente usa só o "crachá" (ID) da categoria.
    // É mais fácil para o Postman mandar só o número 5 do que a categoria inteira.
    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;

}