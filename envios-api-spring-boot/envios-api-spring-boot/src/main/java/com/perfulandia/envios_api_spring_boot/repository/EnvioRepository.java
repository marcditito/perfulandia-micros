package main.java.com.perfulandia.envios_api_spring_boot.repository;

import main.java.com.perfulandia.envios_api_spring_boot.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
