package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categorias")
public class CategoriasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    /**
     * RELACIONAMENTO: @OneToMany (Um para Muitos)
     * Uma Categoria pode estar associada a "Muitos" produtos.
     * * 'mappedBy = "categoria"': Indica que o lado "dono" do relacionamento
     * (quem tem a coluna no banco) é o campo 'categoria' na ProdutosEntity.
     * * 'cascade = CascadeType.ALL': Se você deletar uma categoria,
     * todos os produtos ligados a ela também serão deletados (cuidado aqui!).
     */
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutosEntity> produtos;
}