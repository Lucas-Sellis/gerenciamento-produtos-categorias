package com.lucassellis.gerenciamento_produtos_categorias.controller;

import com.lucassellis.gerenciamento_produtos_categorias.business.Service.CategoriasService;
import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias de produtos")
public class CategoriasController {

    private final CategoriasService service;

    @PostMapping
    @Operation(summary = "Criar nova categoria", description = "Cadastra uma nova categoria no sistema.")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados da categoria inválidos ou nome já existente")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CategoriasDTO> criar(@RequestBody @Valid CategoriasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna uma lista com todas as categorias cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<CategoriasDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os detalhes de uma categoria específica através do seu identificador único.")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<CategoriasDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente.")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada para atualização")
    public ResponseEntity<CategoriasDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriasDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar categoria", description = "Remove uma categoria do sistema pelo ID.")
    @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso (No Content)")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "409", description = "A categoria não pode ser excluída pois possui produtos vinculados")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}