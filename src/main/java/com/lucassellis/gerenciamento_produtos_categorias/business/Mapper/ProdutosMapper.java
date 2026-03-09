package com.lucassellis.gerenciamento_produtos_categorias.business.mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget; // Importante!

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "categoria.id", source = "categoriaId")
    ProdutosEntity toEntity(ProdutosDTO dto);

    @Mapping(target = "categoriaId", source = "categoria.id")
    ProdutosDTO toDto(ProdutosEntity entity);

    // NOVIDADE AQUI: Atualiza a entidade existente com os dados do DTO
    @Mapping(target = "id", ignore = true) // Não queremos mudar o ID do banco
    @Mapping(target = "categoria.id", source = "categoriaId")
    void updateEntityFromDto(ProdutosDTO dto, @MappingTarget ProdutosEntity entity);
}