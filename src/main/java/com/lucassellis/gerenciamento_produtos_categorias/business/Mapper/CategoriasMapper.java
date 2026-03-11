package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import org.mapstruct.Mapper; // Import essencial para o MapStruct
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Avisa ao Spring: "Ó, essa interface é uma fábrica de transformações!"
public interface CategoriasMapper {

    // "Transforma Papel (DTO) em Motor (Entity)":
    // Ignoramos o ID porque quem manda o número é o Banco de Dados.
    @Mapping(target = "id", ignore = true)
    CategoriasEntity toEntity(CategoriasDTO dto);

    // "Transforma Motor (Entity) em Painel (DTO)":
    // Mostra pro usuário só o que ele precisa ver.
    CategoriasDTO toDto(CategoriasEntity entity);

    /**
     * @MappingTarget (O Alvo):
     * Não joga o brinquedo fora! Só pega a tinta nova do DTO e pinta
     * por cima da Entity que já existe (Update).
     */
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CategoriasDTO dto, @MappingTarget CategoriasEntity entity);
}