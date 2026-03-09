// CategoriasDTO.java
package com.lucassellis.gerenciamento_produtos_categorias.business.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriasDTO {

    private Long id;

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;

}