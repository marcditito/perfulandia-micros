package main.java.com.perfulandia.envios_api_spring_boot.repository;

import com.perfulandia.envios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}