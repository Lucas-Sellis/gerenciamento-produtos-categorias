package com.lucassellis.gerenciamento_produtos_categorias.controller;

import com.lucassellis.gerenciamento_produtos_categorias.business.Service.CategoriasService;
import com.lucassellis.gerenciamento_produtos_categorias.business.dto.CategoriasDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriasController {

    private final CategoriasService service;

    @PostMapping
    public ResponseEntity<CategoriasDTO> criar(@RequestBody @Valid CategoriasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriasDTO dto) {
        // No Service de Categorias, você pode criar um método atualizar similar ao de Produtos
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}