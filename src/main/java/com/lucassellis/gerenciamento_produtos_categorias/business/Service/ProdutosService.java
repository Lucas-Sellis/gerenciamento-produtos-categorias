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


    private final ProdutosMapper mapper;

    @Transactional
    public ProdutosDTO criar(ProdutosDTO dto) {

        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro: A categoria " + dto.getCategoriaId() + " não existe!");
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
                .orElseThrow(() -> new ResourceNotFoundException("Produto não localizado."));
    }

    @Transactional
    public ProdutosDTO atualizar(Long id, ProdutosDTO dto) {

        ProdutosEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro: A nova categoria informada não existe!");
        }
        mapper.updateEntityFromDto(dto, entity);
        entity.setId(id);

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não existe.");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Erro ao deletar produto.");
        }
    }

}