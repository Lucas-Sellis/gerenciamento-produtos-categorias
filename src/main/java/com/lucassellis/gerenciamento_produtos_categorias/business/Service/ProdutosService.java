package com.lucassellis.gerenciamento_produtos_categorias.business.Service;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;
import com.lucassellis.gerenciamento_produtos_categorias.business.Mapper.ProdutosMapper;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.exceptions.ConflictException;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.exceptions.ResourceNotFoundException;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.CategoriasRepository;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository repository;
    private final CategoriasRepository categoriasRepository;
    private final ProdutosMapper mapper ;

    @Transactional
    public ProdutosDTO criar(ProdutosDTO dto) {
        // Se a categoria não existe, lançamos o 404 (ResourceNotFound) com a mensagem clara
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro ao criar produto: A categoria " + dto.getCategoriaId() + " não existe!");
        }

        ProdutosEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<ProdutosDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ProdutosDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não localizado."));
    }

    @Transactional
    public ProdutosDTO atualizar(Long id, ProdutosDTO dto) {
        // 1. Verifica se o produto existe
        ProdutosEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar: Produto ID " + id + " não encontrado."));

        // 2. Verifica se a nova categoria informada existe
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro na atualização: A categoria " + dto.getCategoriaId() + " não existe!");
        }

        // 3. Atualiza os campos
        mapper.updateEntityFromDto(dto, entity);
        entity.setId(id); // Garante que o ID permaneça o correto

        return mapper.toDto(repository.save(entity));
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