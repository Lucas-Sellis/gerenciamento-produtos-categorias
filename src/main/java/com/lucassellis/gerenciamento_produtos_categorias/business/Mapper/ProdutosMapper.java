package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "categoria.id", source = "categoriaId")
    ProdutosEntity toEntity(ProdutosDTO dto);

    @Mapping(target = "categoriaId", source = "categoria.id")
    ProdutosDTO toDto(ProdutosEntity entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria.id", source = "categoriaId")
    void updateEntityFromDto(ProdutosDTO dto, @MappingTarget ProdutosEntity entity);

}