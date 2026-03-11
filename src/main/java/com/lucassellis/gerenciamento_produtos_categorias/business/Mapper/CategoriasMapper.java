package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface CategoriasMapper {

    @Mapping(target = "id", ignore = true)
    CategoriasEntity toEntity(CategoriasDTO dto);

    CategoriasDTO toDto(CategoriasEntity entity);


    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CategoriasDTO dto, @MappingTarget CategoriasEntity entity);

}