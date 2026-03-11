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

    // Aqui a coluna 'nome' é UNIQUE (não aceita nomes repetidos) e NOT NULL (obrigatória).
    // O banco de dados vai travar se tentarem salvar duas categorias com o mesmo nome.
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    /**
     * RELACIONAMENTO: 1 para Muitos (@OneToMany)
     * Significa que UMA categoria pode ter VÁRIOS produtos dentro dela.
     * * 'mappedBy = "categoria"': Isso avisa ao Hibernate que quem manda no relacionamento
     * (quem guarda a chave estrangeira no banco) é o campo 'categoria' lá na classe Produto.
     * * List<ProdutosEntity>: É a nossa lista que guarda todos os produtos que pertencem a essa categoria.
     */
    @OneToMany(mappedBy = "categoria")
    private List<ProdutosEntity> produtos;

    // Resumo para estudo:
    // Muitos produtos podem estar ligados a esta única categoria.
    // A Categoria aqui é o lado "Pai" que enxerga a lista de "Filhos" (produtos).
}