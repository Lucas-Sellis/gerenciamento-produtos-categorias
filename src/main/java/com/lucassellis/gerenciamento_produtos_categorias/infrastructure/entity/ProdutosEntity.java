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

    // Define que o campo é obrigatório no banco de dados (NOT NULL)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    // Double permite operações matemáticas e casas decimais para valores monetários
    @Column(name = "preco")
    private Double preco;

    /**
     * RELACIONAMENTO: @ManyToOne
     * Muitos (Produtos) para Um (Categoria).
     * Indica que vários registros desta tabela podem apontar para a mesma Categoria.
     * @JoinColumn define a Foreign Key (FK) 'categoria_id' no banco de dados.
     */
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriasEntity categoria;
}