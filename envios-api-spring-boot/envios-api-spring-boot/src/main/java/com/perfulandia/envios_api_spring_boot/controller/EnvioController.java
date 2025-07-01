package main.java.com.perfulandia.envios_api_spring_boot.controller;

import main.java.com.perfulandia.envios_api_spring_boot.model.Envio;
import main.java.com.perfulandia.envios_api_spring_boot.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService service;

    @PostMapping
    public ResponseEntity<EntityModel<Envio>> crear(@RequestBody Envio envio) {
        Envio nuevoEnvio = service.guardar(envio);
        EntityModel<Envio> resource = EntityModel.of(nuevoEnvio);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).obtener(nuevoEnvio.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).listar()).withRel("envios"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Envio>>> listar() {
        List<EntityModel<Envio>> envios = service.listar().stream()
                .map(envio -> {
                    EntityModel<Envio> resource = EntityModel.of(envio);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).obtener(envio.getId())).withSelfRel());
                    return resource;
                }).collect(Collectors.toList());

        CollectionModel<EntityModel<Envio>> collection = CollectionModel.of(envios);
        collection.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).listar()).withSelfRel());
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(envio -> {
                    EntityModel<Envio> resource = EntityModel.of(envio);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).obtener(id)).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).listar()).withRel("envios"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        return service.actualizar(id, envio)
                .map(actualizado -> {
                    EntityModel<Envio> resource = EntityModel.of(actualizado);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).obtener(actualizado.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class).listar()).withRel("envios"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
