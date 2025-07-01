package main.java.com.perfulandia.envios_api_spring_boot.service;

import main.java.com.perfulandia.envios_api_spring_boot.model.Envio;
import main.java.com.perfulandia.envios_api_spring_boot.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    public Envio guardar(Envio envio) {
        return repository.save(envio);
    }

    public List<Envio> listar() {
        return repository.findAll();
    }

    public Optional<Envio> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Envio> actualizar(Long id, Envio envio) {
        return repository.findById(id)
                .map(envioExistente -> {
                    envioExistente.setFechaEnvio(envio.getFechaEnvio());
                    envioExistente.setDireccionDestino(envio.getDireccionDestino());
                    envioExistente.setEstado(envio.getEstado());
                    envioExistente.setIdVenta(envio.getIdVenta());
                    return repository.save(envioExistente);
                });
    }

    public boolean eliminar(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
