package com.lucassellis.gerenciamento_produtos_categorias.business.Service;

import com.lucassellis.gerenciamento_produtos_categorias.business.Mapper.CategoriasMapper;
import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.CategoriasEntity;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.exceptions.ConflictException;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.exceptions.ResourceNotFoundException;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.CategoriasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriasService {

    private final CategoriasRepository repository;
    private final CategoriasMapper mapper;

    // @Transactional: "Tudo ou Nada". Se o banco falhar no meio do caminho,
    // ele desfaz tudo para não deixar lixo no sistema.
    @Transactional
    public CategoriasDTO criar(CategoriasDTO dto) {
        try {
            CategoriasEntity entity = mapper.toEntity(dto);
            return mapper.toDto(repository.save(entity));
        } catch (DataIntegrityViolationException e) {
            // Se tentar criar um nome que já existe (Unique), lança o erro de Conflito.
            throw new ConflictException("Categoria já existe.");
        }
    }

    @Transactional
    public CategoriasDTO atualizar(Long id, CategoriasDTO dto) {
        // Primeiro: Verifica se a categoria existe. Se não existir, lança o erro 404 (NotFound).
        CategoriasEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + id + " não encontrada."));

        // updateEntityFromDto: Ele pega o que você escreveu no DTO e "cola" dentro da Entity que achamos no banco.
        mapper.updateEntityFromDto(dto, entity);
        entity.setId(id); // Trava o ID para garantir que estamos mexendo no brinquedo certo.

        return mapper.toDto(repository.save(entity));
    }

    public List<CategoriasDTO> listar() {
        // Stream/Collect: É como uma esteira de fábrica. Pega as Entities,
        // transforma uma por uma em DTO e no final junta tudo numa lista.
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoriasDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não existe."));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não existe.");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // IMPORTANTE: Se tiver produtos dentro da categoria, o banco não deixa deletar.
            throw new ConflictException("Não é possível deletar: existem produtos vinculados.");
        }
    }
}