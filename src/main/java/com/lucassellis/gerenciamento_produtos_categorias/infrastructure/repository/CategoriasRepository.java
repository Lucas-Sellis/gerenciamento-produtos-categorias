package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository;

import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriasRepository extends JpaRepository<CategoriasEntity, Long> {

}
