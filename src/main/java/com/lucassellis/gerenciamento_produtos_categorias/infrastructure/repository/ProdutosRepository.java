package com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository;

import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {
//    Repository Vazio: O seu secretário sabe fazer o básico: guardar o brinquedo, jogar fora ou listar todos os que estão na caixa.
//
//    Repository com Métodos: Você está dando um treinamento extra para o seu secretário.
//
//    Você diz: "Ó, toda vez que eu te pedir 'BusquePorCor', você olha a cor do brinquedo e me traz só os azuis".
}
