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
    private final CategoriasRepository categoriasRepository; // Precisamos dele para validar a "caixa" (categoria)
    private final ProdutosMapper mapper ;

    @Transactional
    public ProdutosDTO criar(ProdutosDTO dto) {
        // Antes de criar o brinquedo, verifica se a caixa (categoria) existe no banco.
        // Se não existir, a gente para tudo aqui com um erro 404.
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro: A categoria " + dto.getCategoriaId() + " não existe!");
        }

        ProdutosEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<ProdutosDTO> listar() {
        // Pega todos os produtos do banco e transforma na lista bonitinha (DTO) para o usuário.
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
        // Primeiro: O produto existe?
        ProdutosEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        // Segundo: Se o usuário quer mudar o produto de categoria, essa nova categoria existe?
        if (!categoriasRepository.existsById(dto.getCategoriaId())) {
            throw new ResourceNotFoundException("Erro: A nova categoria informada não existe!");
        }

        // Terceiro: Atualiza o que mudou (nome, preço, etc) mantendo o mesmo ID.
        mapper.updateEntityFromDto(dto, entity);
        entity.setId(id);

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void deletar(Long id) {
        // Se tentar deletar algo que não está lá, avisa que não encontrou.
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não existe.");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Caso o banco trave por alguma regra de segurança.
            throw new ConflictException("Erro ao deletar produto.");
        }
    }
}