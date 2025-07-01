package main.java.com.perfulandia.envios_api_spring_boot.controller;

import com.perfulandia.envios.model.Envio;
import com.perfulandia.envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable Long id) {
        Optional<Envio> envio = envioService.obtenerPorId(id);
        return envio.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.guardar(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        if (!envioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        envio.setId(id);
        return ResponseEntity.ok(envioService.guardar(envio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long id) {
        if (!envioService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        envioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
