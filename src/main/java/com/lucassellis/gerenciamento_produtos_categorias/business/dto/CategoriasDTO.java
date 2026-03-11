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

    // "Regra do Postman": Se o usuário tentar mandar um nome vazio,
    // o Spring dá um erro antes mesmo de chegar no banco de dados.
    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;

    /**
     * O QUE É O DTO (Raciocínio de Estudo):
     * 1. Isolar a Entity: A Entity fica quietinha cuidando do banco.
     * O DTO é quem viaja pela internet (JSON).
     * 2. Segurança: A gente só mostra pro usuário o que a gente quer.
     * Nesse caso, só o ID e o Nome da categoria.
     */
}