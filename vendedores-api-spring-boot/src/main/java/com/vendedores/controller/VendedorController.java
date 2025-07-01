package com.vendedores.controller;

import com.vendedores.model.Vendedor;
import com.vendedores.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<Vendedor> registrar(@RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(vendedorService.registrarVendedor(vendedor));
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> obtenerTodos() {
        return ResponseEntity.ok(vendedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarPorId(@PathVariable Long id) {
        return vendedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        vendedorService.eliminarPorId(id);
        return ResponseEntity.ok("Vendedor eliminado correctamente.");
    }
}

