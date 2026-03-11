package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository;

import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriasRepository extends JpaRepository<CategoriasEntity, Long> {
// Aqui a gente "pega emprestado" todos os poderes do Spring Data JPA.
    // É como ganhar uma varinha mágica: a gente não escreve nada,
    // mas o Spring já entende que queremos Salvar, Deletar, Buscar e Alterar.

    // CategoriasEntity: Qual brinquedo vamos guardar? (A Entidade)
    // Long: Qual o tipo do "RG" (ID) desse brinquedo? (O tipo da Chave Primária)
}
