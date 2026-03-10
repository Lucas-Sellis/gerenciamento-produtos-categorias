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


    @Transactional
    public CategoriasDTO criar(CategoriasDTO dto) {
        try {
            CategoriasEntity entity = mapper.toEntity(dto);
            return mapper.toDto(repository.save(entity));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Categoria já existe.");
        }
    }

    @Transactional
    public CategoriasDTO atualizar(Long id, CategoriasDTO dto) {
        // Mensagem específica para o fluxo de atualização
        CategoriasEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar: Categoria com ID " + id + " não encontrada."));

        mapper.updateEntityFromDto(dto, entity);
        entity.setId(id); // Garante que o ID do banco não mude

        return mapper.toDto(repository.save(entity));
    }

    public List<CategoriasDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoriasDTO buscarPorId(Long id) {
        // Mensagem direta para a busca
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + id + " não existe em nossa base de dados."));
    }

    @Transactional
    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Falha ao deletar: Categoria com ID " + id + " não existe.");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Não é possível deletar a categoria pois existem produtos vinculados.");
        }
    }
}