package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.mapstruct.Mapper; // Import essencial para o MapStruct

@Mapper(componentModel = "spring")
public interface CategoriasMapper {

    CategoriasEntity toEntity(CategoriasDTO dto);

    CategoriasDTO toDto(CategoriasEntity entity);
}