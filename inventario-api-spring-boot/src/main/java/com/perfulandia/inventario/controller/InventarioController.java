package com.perfulandia.inventario.controller;

import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<EntityModel<Inventario>> guardar(@RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.guardarInventario(inventario);
        EntityModel<Inventario> recurso = EntityModel.of(nuevo,
                linkTo(methodOn(InventarioController.class).buscarPorId(nuevo.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).obtenerTodos()).withRel("inventarios"));
        return ResponseEntity.ok(recurso);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> obtenerTodos() {
        List<EntityModel<Inventario>> inventarios = inventarioService.obtenerTodos().stream()
                .map(inv -> EntityModel.of(inv,
                        linkTo(methodOn(InventarioController.class).buscarPorId(inv.getId())).withSelfRel(),
                        linkTo(methodOn(InventarioController.class).obtenerTodos()).withRel("inventarios")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Inventario>> recursos = CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioController.class).obtenerTodos()).withSelfRel());

        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Inventario>> buscarPorId(@PathVariable Long id) {
        return inventarioService.buscarPorId(id)
                .map(inv -> EntityModel.of(inv,
                        linkTo(methodOn(InventarioController.class).buscarPorId(id)).withSelfRel(),
                        linkTo(methodOn(InventarioController.class).obtenerTodos()).with
