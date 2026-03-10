package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.mapstruct.Mapper; // Import essencial para o MapStruct
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriasMapper {

    CategoriasEntity toEntity(CategoriasDTO dto);

    CategoriasDTO toDto(CategoriasEntity entity);

    // Adicione isto para permitir a atualização automática
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CategoriasDTO dto, @MappingTarget CategoriasEntity entity);
}