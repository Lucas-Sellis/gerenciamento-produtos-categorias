package com.lucassellis.gerenciamento_produtos_categorias.business.Service;

import com.lucassellis.gerenciamento_produtos_categorias.business.Mapper.CategoriasMapper;
import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.CategoriasRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriasService {

    private final CategoriasRepository repository;

    private final CategoriasMapper mapper;

    public CategoriasDTO criar(CategoriasDTO dto) {

        CategoriasEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }


    public CategoriasDTO atualizar(Long id, CategoriasDTO dto) {
        // 1. Busca a categoria existente
        CategoriasEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para atualizar"));

        // 2. O MapStruct transfere os dados do DTO para a Entity
        mapper.updateEntityFromDto(dto, entity);

        // 3. Salva e retorna o DTO
        return mapper.toDto(repository.save(entity));
    }

    public List<CategoriasDTO> listar() {

        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public CategoriasDTO buscarPorId(Long id) {

        CategoriasEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return mapper.toDto(entity);
    }

    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada para deletar");
        }
        repository.deleteById(id);
    }


}
