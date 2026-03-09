package com.lucassellis.gerenciamento_produtos_categorias.business.Service;

import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;

import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.entity.ProdutosEntity;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.CategoriasRepository;
import com.lucassellis.gerenciamento_produtos_categorias.infrastructure.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository repository;

    private final CategoriasRepository categoriasRepository;

    private final com.lucassellis.gerenciamento_produtos_categorias.business.mapper.ProdutosMapper mapper;

    public ProdutosDTO criar(ProdutosDTO dto) {

        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new RuntimeException("Erro: A categoria " + dto.getCategoriaId() + " não existe!");
        }
        ProdutosEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<ProdutosDTO> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toDto) // USAMOS O MAPPER AQUI!
                .collect(Collectors.toList());
    }

    public ProdutosDTO buscarPorId(Long id) {

        ProdutosEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        return mapper.toDto(entity); // CONVERTEMOS PARA DTO
    }

    public ProdutosDTO atualizar(Long id, ProdutosDTO dto) {
        // 1. Busca o produto no banco
        ProdutosEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // 2. Valida se a categoria enviada no DTO existe
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new RuntimeException("Categoria informada não existe!");
        }

        // 3. A MÁGICA: O MapStruct atualiza os campos da 'entity' automaticamente
        mapper.updateEntityFromDto(dto, entity);

        // 4. Salva a entidade já atualizada e retorna o DTO
        return mapper.toDto(repository.save(entity));
    }

    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Produto não encontrado");
        }
        repository.deleteById(id);
    }

}