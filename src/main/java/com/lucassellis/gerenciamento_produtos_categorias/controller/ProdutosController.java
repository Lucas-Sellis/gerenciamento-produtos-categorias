package com.lucassellis.gerenciamento_produtos_categorias.controller;

import com.lucassellis.gerenciamento_produtos_categorias.business.Service.ProdutosService;
import com.lucassellis.gerenciamento_produtos_categorias.business.dto.ProdutosDTO;
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
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos e seus vínculos com categorias")
public class ProdutosController {

    private final ProdutosService service;

    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto vinculado a uma categoria existente via ID.")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou erro de validação")
    @ApiResponse(responseCode = "404", description = "Categoria informada não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<ProdutosDTO> criar(@RequestBody @Valid ProdutosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista completa de produtos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ProdutosDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico através do seu identificador único.")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<ProdutosDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente.")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto ou Categoria não encontrados")
    public ResponseEntity<ProdutosDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutosDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove permanentemente um produto do sistema.")
    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}