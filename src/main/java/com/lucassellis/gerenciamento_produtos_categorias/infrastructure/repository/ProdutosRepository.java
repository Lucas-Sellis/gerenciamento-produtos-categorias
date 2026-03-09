package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository;

import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {

}
