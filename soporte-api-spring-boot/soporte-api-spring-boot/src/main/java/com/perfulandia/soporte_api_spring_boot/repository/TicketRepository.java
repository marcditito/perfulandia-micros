package com.perfulandia.soporte_api_spring_boot.repository;

import com.perfulandia.soporte_api_spring_boot.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
}
