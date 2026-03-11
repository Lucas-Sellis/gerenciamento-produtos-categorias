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
@Tag(name = "Categorias", description = "Documentação do Swagger")
public class CategoriasController {

    private final CategoriasService service;

    @PostMapping
    // @Operation e @ApiResponse: É o "manual de instruções" que aparece na tela verde do Swagger.
    @Operation(summary = "Criar nova categoria")
    public ResponseEntity<CategoriasDTO> criar(@RequestBody @Valid CategoriasDTO dto) {
        // @Valid: Chama o "segurança" para ver se o nome não está em branco.
        // HttpStatus.CREATED: Retorna o código 201 (Sucesso total ao criar algo novo).
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> listar() {
        // ResponseEntity.ok: Retorna o código 200 (Beleza, tudo certo!).
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDTO> buscarPorId(@PathVariable Long id) {
        // @PathVariable: Pega o número que você digitar na URL (ex: /categorias/5).
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriasDTO dto) {
        // Recebe o ID da URL e o "Papel" (DTO) com os novos dados no corpo da mensagem.
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        // .noContent().build(): Retorna o código 204.
        // Significa: "Deu certo, mas não tenho nada para te mostrar agora porque o registro sumiu".
        return ResponseEntity.noContent().build();
    }
}