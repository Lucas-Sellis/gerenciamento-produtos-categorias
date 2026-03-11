package com.lucassellis.gerenciamento_produtos_categorias.business.Mapper;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget; // Importante!

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    // Mira no ID da caixa (categoria.id) e diz que ele vem do número (categoriaId) do papel.
    @Mapping(target = "categoria.id", source = "categoriaId")
    ProdutosEntity toEntity(ProdutosDTO dto);

    // Faz o caminho de volta: pega o ID que está dentro da caixa e coloca no papel (DTO).
    @Mapping(target = "categoriaId", source = "categoria.id")
    ProdutosDTO toDto(ProdutosEntity entity);

    /**
     * ATUALIZAÇÃO (@MappingTarget):
     * Pega o brinquedo que já existe no banco e só muda as peças (nome, preço, etc).
     * Ignora o ID (não mude o RG do brinquedo!).
     * Garante que o novo número da caixa (categoriaId) seja colado na caixa certa.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria.id", source = "categoriaId")
    void updateEntityFromDto(ProdutosDTO dto, @MappingTarget ProdutosEntity entity);
}