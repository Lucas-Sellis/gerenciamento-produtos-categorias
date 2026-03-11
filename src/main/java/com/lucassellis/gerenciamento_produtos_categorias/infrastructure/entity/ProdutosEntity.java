package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "produtos")
public class ProdutosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O nome do brinquedo. Não pode estar em branco, senão a gente não sabe o que é!
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    // O preço do brinquedo (ex: 10.50). Sempre que for preço usar double
    @Column(name = "preco")
    private Double preco;

    /**
     * RELACIONAMENTO: @ManyToOne
     * Regra: "Muitos carrinhos (produtos) entram em uma única caixa (categoria)".
     */
    @ManyToOne
    // É obrigatório (nullable = false) porque todo brinquedo tem que ter uma caixa!
    @JoinColumn(name = "categoria_id", nullable = false)     // "Ajunta" o produto com a categoria dele.
    private CategoriasEntity categoria;
}
